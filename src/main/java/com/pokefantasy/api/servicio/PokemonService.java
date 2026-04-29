package com.pokefantasy.api.servicio;

import com.pokefantasy.api.modelo.Pokemon;
import com.pokefantasy.api.repositorio.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> obtenerTodos() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> obtenerPorId(int id) {
        return pokemonRepository.findById(id);
    }

    public List<Pokemon> obtenerPorEquipo(int idUsuario) {
        return pokemonRepository.findByEquipoUsuario(idUsuario);
    }

    public List<Pokemon> obtenerDisponiblesEnMercado() {
        return pokemonRepository.findDisponiblesEnMercado();
    }
}
