package com.pokefantasy.api.controlador;

import com.pokefantasy.api.dto.CrearOfertaRequest;
import com.pokefantasy.api.dto.HistorialOfertaDTO;
import com.pokefantasy.api.dto.OfertaEnviadaDTO;
import com.pokefantasy.api.dto.OfertaRecibidaDTO;
import com.pokefantasy.api.modelo.Oferta;
import com.pokefantasy.api.servicio.OfertaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    private final OfertaService ofertaService;

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @PostMapping
    public Oferta crear(@Valid @RequestBody CrearOfertaRequest request) {
        return ofertaService.crearOferta(
                request.getIdPokemon(),
                request.getIdOferente(),
                request.getPrecioOferta(),
                request.getIdPropietario());
    }

    @GetMapping("/enviadas/{idOferente}")
    public List<OfertaEnviadaDTO> obtenerEnviadas(@PathVariable int idOferente) {
        return ofertaService.obtenerEnviadas(idOferente);
    }

    @GetMapping("/recibidas/{idPropietario}")
    public List<OfertaRecibidaDTO> obtenerRecibidas(@PathVariable int idPropietario) {
        return ofertaService.obtenerRecibidas(idPropietario);
    }

    @GetMapping("/historial/{idUsuario}")
    public List<HistorialOfertaDTO> obtenerHistorial(@PathVariable int idUsuario) {
        return ofertaService.obtenerHistorial(idUsuario);
    }

    @GetMapping("/pendiente/pokemon/{idPokemon}/oferente/{idOferente}")
    public boolean existePendiente(@PathVariable int idPokemon, @PathVariable int idOferente) {
        return ofertaService.existePendiente(idPokemon, idOferente);
    }

    @PostMapping("/{idOferta}/aceptar")
    public ResponseEntity<String> aceptar(@PathVariable int idOferta) {
        if (ofertaService.aceptarOferta(idOferta)) {
            return ResponseEntity.ok("Oferta aceptada");
        }
        return ResponseEntity.badRequest().body("No se pudo aceptar (saldo insuficiente, estado inválido o id no encontrado)");
    }

    @PostMapping("/{idOferta}/rechazar")
    public ResponseEntity<String> rechazar(@PathVariable int idOferta) {
        if (ofertaService.rechazarOferta(idOferta)) {
            return ResponseEntity.ok("Oferta rechazada");
        }
        return ResponseEntity.badRequest().body("No se pudo rechazar (id no encontrado)");
    }
}
