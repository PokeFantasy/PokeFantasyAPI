package com.pokefantasy.api.controlador;

import com.pokefantasy.api.modelo.Pokemon;
import com.pokefantasy.api.servicio.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> obtenerTodos() {
        return pokemonService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> obtenerPorId(@PathVariable int id) {
        return pokemonService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/equipo/{idUsuario}")
    public List<Pokemon> obtenerPorEquipo(@PathVariable int idUsuario) {
        return pokemonService.obtenerPorEquipo(idUsuario);
    }

    @GetMapping("/mercado")
    public List<Pokemon> obtenerDisponiblesEnMercado() {
        return pokemonService.obtenerDisponiblesEnMercado();
    }
}
