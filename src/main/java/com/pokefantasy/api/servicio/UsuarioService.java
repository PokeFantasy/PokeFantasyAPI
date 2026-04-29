package com.pokefantasy.api.servicio;

import com.pokefantasy.api.modelo.Usuario;
import com.pokefantasy.api.repositorio.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> login(String email, String contrasena) {
        return usuarioRepository.findByEmailAndContrasena(email, contrasena);
    }

    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email");
        }
        if (usuario.getSaldo() == 0) {
            usuario.setSaldo(1000.00);
        }
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(int id) {
        return usuarioRepository.findById(id);
    }
}
