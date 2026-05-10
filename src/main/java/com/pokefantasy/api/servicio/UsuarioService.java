package com.pokefantasy.api.servicio;

import com.pokefantasy.api.modelo.Usuario;
import com.pokefantasy.api.repositorio.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Autentica al usuario. Soporta dos formatos de contraseña en BD:
     * <ul>
     *   <li><b>BCrypt</b> (empieza por {@code $2a$/$2b$/$2y$}): se valida con
     *       {@link PasswordEncoder#matches(CharSequence, String)}.</li>
     *   <li><b>Texto plano</b> (usuarios pre-migración): se compara directamente
     *       y, si coincide, se hashea y se persiste en la misma operación de
     *       login. La próxima vez ya entrará por la rama BCrypt.</li>
     * </ul>
     * Esto permite habilitar BCrypt sin invalidar las cuentas existentes.
     */
    public Optional<Usuario> login(String email, String contrasena) {
        Optional<Usuario> opt = usuarioRepository.findByEmail(email);
        if (opt.isEmpty()) {
            return Optional.empty();
        }
        Usuario usuario = opt.get();
        String almacenada = usuario.getContrasena();

        if (esBcrypt(almacenada)) {
            return passwordEncoder.matches(contrasena, almacenada)
                    ? Optional.of(usuario)
                    : Optional.empty();
        }

        // Fallback texto plano (cuentas anteriores a la migración BCrypt)
        if (!almacenada.equals(contrasena)) {
            return Optional.empty();
        }
        // Migración transparente: hasheamos y guardamos al vuelo
        usuario.setContrasena(passwordEncoder.encode(contrasena));
        usuarioRepository.save(usuario);
        return Optional.of(usuario);
    }

    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email");
        }
        if (usuario.getSaldo() == 0) {
            usuario.setSaldo(1000.00);
        }
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(int id) {
        return usuarioRepository.findById(id);
    }

    private static boolean esBcrypt(String s) {
        return s != null && (s.startsWith("$2a$") || s.startsWith("$2b$") || s.startsWith("$2y$"));
    }
}
