package com.pokefantasy.api.controlador;

import com.pokefantasy.api.dto.ClasificacionEntry;
import com.pokefantasy.api.dto.CrearLigaRequest;
import com.pokefantasy.api.modelo.Liga;
import com.pokefantasy.api.servicio.LigaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ligas")
public class LigaController {

    private final LigaService ligaService;

    public LigaController(LigaService ligaService) {
        this.ligaService = ligaService;
    }

    @PostMapping
    public Liga crear(@RequestBody CrearLigaRequest request) {
        return ligaService.crearLiga(request.getNombre(), request.getIdCreador());
    }

    @GetMapping("/disponibles/usuario/{idUsuario}")
    public List<Liga> obtenerDisponibles(@PathVariable int idUsuario) {
        return ligaService.obtenerDisponibles(idUsuario);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Liga> obtenerLigasDeUsuario(@PathVariable int idUsuario) {
        return ligaService.obtenerLigasDeUsuario(idUsuario);
    }

    @PostMapping("/{idLiga}/usuario/{idUsuario}")
    public ResponseEntity<Void> unirse(@PathVariable int idLiga, @PathVariable int idUsuario) {
        if (ligaService.unirse(idUsuario, idLiga)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idLiga}/usuario/{idUsuario}")
    public ResponseEntity<Void> salir(@PathVariable int idLiga, @PathVariable int idUsuario) {
        if (ligaService.salirDeLiga(idUsuario, idLiga)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idLiga}/clasificacion")
    public List<ClasificacionEntry> obtenerClasificacion(@PathVariable int idLiga) {
        return ligaService.obtenerClasificacion(idLiga);
    }

    @GetMapping("/{idLiga}/participantes")
    public List<Integer> obtenerParticipantes(@PathVariable int idLiga) {
        return ligaService.obtenerParticipantes(idLiga);
    }

    @PostMapping("/{idLiga}/usuario/{idUsuario}/puntos")
    public ResponseEntity<Void> sumarPuntos(@PathVariable int idLiga,
                                             @PathVariable int idUsuario,
                                             @RequestBody Map<String, Integer> body) {
        Integer puntos = body.get("puntos");
        if (puntos == null) {
            return ResponseEntity.badRequest().build();
        }
        ligaService.sumarPuntos(idUsuario, idLiga, puntos);
        return ResponseEntity.ok().build();
    }
}
