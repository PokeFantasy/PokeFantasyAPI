package com.pokefantasy.api.controlador;

import com.pokefantasy.api.modelo.Equipo;
import com.pokefantasy.api.servicio.EquipoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @PostMapping("/usuario/{idUsuario}")
    public Equipo obtenerOCrear(@PathVariable int idUsuario) {
        return equipoService.obtenerOCrearEquipo(idUsuario);
    }

    @PostMapping("/{idEquipo}/pokemon/{idPokemon}")
    public ResponseEntity<Void> agregarPokemon(@PathVariable int idEquipo,
                                                @PathVariable int idPokemon) {
        if (equipoService.agregarPokemon(idEquipo, idPokemon)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idEquipo}/pokemon/{idPokemon}")
    public ResponseEntity<Void> quitarPokemon(@PathVariable int idEquipo,
                                               @PathVariable int idPokemon) {
        if (equipoService.quitarPokemon(idEquipo, idPokemon)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idEquipo}/contar")
    public int contarPokemon(@PathVariable int idEquipo) {
        return equipoService.contarPokemon(idEquipo);
    }
}
