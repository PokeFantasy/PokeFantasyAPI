package com.pokefantasy.api.servicio;

import com.pokefantasy.api.dto.HistorialOfertaDTO;
import com.pokefantasy.api.dto.OfertaEnviadaDTO;
import com.pokefantasy.api.dto.OfertaRecibidaDTO;
import com.pokefantasy.api.modelo.Equipo;
import com.pokefantasy.api.modelo.Oferta;
import com.pokefantasy.api.modelo.Usuario;
import com.pokefantasy.api.repositorio.EquipoPokemonRepository;
import com.pokefantasy.api.repositorio.EquipoRepository;
import com.pokefantasy.api.repositorio.MercadoRepository;
import com.pokefantasy.api.repositorio.OfertaQueryRepository;
import com.pokefantasy.api.repositorio.OfertaRepository;
import com.pokefantasy.api.repositorio.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OfertaService {

    private final OfertaRepository ofertaRepository;
    private final OfertaQueryRepository ofertaQueryRepository;
    private final UsuarioRepository usuarioRepository;
    private final EquipoRepository equipoRepository;
    private final EquipoPokemonRepository equipoPokemonRepository;
    private final MercadoRepository mercadoRepository;

    public OfertaService(OfertaRepository ofertaRepository,
                         OfertaQueryRepository ofertaQueryRepository,
                         UsuarioRepository usuarioRepository,
                         EquipoRepository equipoRepository,
                         EquipoPokemonRepository equipoPokemonRepository,
                         MercadoRepository mercadoRepository) {
        this.ofertaRepository = ofertaRepository;
        this.ofertaQueryRepository = ofertaQueryRepository;
        this.usuarioRepository = usuarioRepository;
        this.equipoRepository = equipoRepository;
        this.equipoPokemonRepository = equipoPokemonRepository;
        this.mercadoRepository = mercadoRepository;
    }

    public Oferta crearOferta(int idPokemon, int idOferente, double precio, int idPropietario) {
        return ofertaRepository.save(new Oferta(idPokemon, idOferente, precio, idPropietario));
    }

    public List<OfertaEnviadaDTO> obtenerEnviadas(int idOferente) {
        return ofertaQueryRepository.obtenerEnviadas(idOferente);
    }

    public List<OfertaRecibidaDTO> obtenerRecibidas(int idPropietario) {
        return ofertaQueryRepository.obtenerRecibidas(idPropietario);
    }

    public List<HistorialOfertaDTO> obtenerHistorial(int idUsuario) {
        return ofertaQueryRepository.obtenerHistorial(idUsuario);
    }

    public boolean existePendiente(int idPokemon, int idOferente) {
        return ofertaRepository.existsByIdPokemonAndIdOferenteAndEstado(idPokemon, idOferente, "pendiente");
    }

    @Transactional
    public boolean rechazarOferta(int idOferta) {
        return ofertaRepository.actualizarEstado(idOferta, "rechazada") > 0;
    }

    @Transactional
    public boolean aceptarOferta(int idOferta) {
        Optional<Oferta> ofertaOpt = ofertaRepository.findById(idOferta);
        if (ofertaOpt.isEmpty() || !"pendiente".equals(ofertaOpt.get().getEstado())) {
            return false;
        }
        Oferta oferta = ofertaOpt.get();

        Optional<Usuario> oferenteOpt = usuarioRepository.findById(oferta.getIdOferente());
        if (oferenteOpt.isEmpty()) {
            return false;
        }
        Usuario oferente = oferenteOpt.get();
        if (oferente.getSaldo() < oferta.getPrecioOferta()) {
            return false;
        }

        usuarioRepository.ajustarSaldo(oferta.getIdOferente(), -oferta.getPrecioOferta());
        usuarioRepository.ajustarSaldo(oferta.getIdPropietario(), oferta.getPrecioOferta());

        Optional<Equipo> equipoPropietario = equipoRepository.findByIdUsuario(oferta.getIdPropietario());
        if (equipoPropietario.isPresent()) {
            equipoPokemonRepository.quitar(equipoPropietario.get().getIdEquipo(), oferta.getIdPokemon());
        }

        Equipo equipoOferente = equipoRepository.findByIdUsuario(oferta.getIdOferente())
                .orElseGet(() -> equipoRepository.save(new Equipo(oferta.getIdOferente())));
        equipoPokemonRepository.agregar(equipoOferente.getIdEquipo(), oferta.getIdPokemon());

        mercadoRepository.deleteByIdPokemon(oferta.getIdPokemon());

        ofertaRepository.actualizarEstado(idOferta, "aceptada");
        ofertaRepository.rechazarOtrasPendientesPorPokemon(oferta.getIdPokemon(), idOferta);

        return true;
    }
}
