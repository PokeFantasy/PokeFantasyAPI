package com.pokefantasy.api.controlador;

import com.pokefantasy.api.dto.FavoritoRequest;
import com.pokefantasy.api.servicio.FavoritoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping
    public ResponseEntity<Void> agregar(@Valid @RequestBody FavoritoRequest request) {
        favoritoService.agregar(request.getIdUsuario(), request.getIdPokemon());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/usuario/{idUsuario}/pokemon/{idPokemon}")
    public ResponseEntity<Void> quitar(@PathVariable int idUsuario, @PathVariable int idPokemon) {
        favoritoService.quitar(idUsuario, idPokemon);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Integer> obtenerFavoritos(@PathVariable int idUsuario) {
        return favoritoService.obtenerFavoritos(idUsuario);
    }

    @GetMapping("/usuario/{idUsuario}/pokemon/{idPokemon}")
    public boolean esFavorito(@PathVariable int idUsuario, @PathVariable int idPokemon) {
        return favoritoService.esFavorito(idUsuario, idPokemon);
    }
}
