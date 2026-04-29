package com.pokefantasy.api.servicio;

import com.pokefantasy.api.dto.OfertaMercadoDTO;
import com.pokefantasy.api.modelo.Equipo;
import com.pokefantasy.api.modelo.Mercado;
import com.pokefantasy.api.modelo.Usuario;
import com.pokefantasy.api.repositorio.EquipoPokemonRepository;
import com.pokefantasy.api.repositorio.EquipoRepository;
import com.pokefantasy.api.repositorio.MercadoQueryRepository;
import com.pokefantasy.api.repositorio.MercadoRepository;
import com.pokefantasy.api.repositorio.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MercadoService {

    private final MercadoRepository mercadoRepository;
    private final MercadoQueryRepository mercadoQueryRepository;
    private final UsuarioRepository usuarioRepository;
    private final EquipoRepository equipoRepository;
    private final EquipoPokemonRepository equipoPokemonRepository;

    public MercadoService(MercadoRepository mercadoRepository,
                          MercadoQueryRepository mercadoQueryRepository,
                          UsuarioRepository usuarioRepository,
                          EquipoRepository equipoRepository,
                          EquipoPokemonRepository equipoPokemonRepository) {
        this.mercadoRepository = mercadoRepository;
        this.mercadoQueryRepository = mercadoQueryRepository;
        this.usuarioRepository = usuarioRepository;
        this.equipoRepository = equipoRepository;
        this.equipoPokemonRepository = equipoPokemonRepository;
    }

    public List<OfertaMercadoDTO> obtenerOfertas() {
        return mercadoQueryRepository.obtenerOfertas();
    }

    public Mercado ponerEnVenta(int idPokemon, double precio, int idVendedor) {
        return mercadoRepository.save(new Mercado(idPokemon, precio, idVendedor));
    }

    public boolean estaEnVenta(int idPokemon) {
        return mercadoRepository.existsByIdPokemon(idPokemon);
    }

    @Transactional
    public boolean comprar(int idMercado, int idComprador) {
        Optional<Mercado> mercadoOpt = mercadoRepository.findById(idMercado);
        if (mercadoOpt.isEmpty()) {
            return false;
        }
        Mercado mercado = mercadoOpt.get();

        Optional<Usuario> compradorOpt = usuarioRepository.findById(idComprador);
        if (compradorOpt.isEmpty()) {
            return false;
        }
        Usuario comprador = compradorOpt.get();

        if (comprador.getSaldo() < mercado.getPrecio()) {
            return false;
        }

        usuarioRepository.ajustarSaldo(idComprador, -mercado.getPrecio());
        usuarioRepository.ajustarSaldo(mercado.getIdVendedor(), mercado.getPrecio());

        Optional<Equipo> equipoVendedorOpt = equipoRepository.findByIdUsuario(mercado.getIdVendedor());
        if (equipoVendedorOpt.isPresent()) {
            equipoPokemonRepository.quitar(equipoVendedorOpt.get().getIdEquipo(), mercado.getIdPokemon());
        }

        Equipo equipoComprador = equipoRepository.findByIdUsuario(idComprador)
                .orElseGet(() -> equipoRepository.save(new Equipo(idComprador)));
        equipoPokemonRepository.agregar(equipoComprador.getIdEquipo(), mercado.getIdPokemon());

        mercadoRepository.deleteById(idMercado);
        return true;
    }
}
