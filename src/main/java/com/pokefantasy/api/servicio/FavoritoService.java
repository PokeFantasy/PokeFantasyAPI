package com.pokefantasy.api.servicio;

import com.pokefantasy.api.repositorio.FavoritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    public FavoritoService(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    public void agregar(int idUsuario, int idPokemon) {
        favoritoRepository.agregar(idUsuario, idPokemon);
    }

    public void quitar(int idUsuario, int idPokemon) {
        favoritoRepository.quitar(idUsuario, idPokemon);
    }

    public boolean esFavorito(int idUsuario, int idPokemon) {
        return favoritoRepository.existe(idUsuario, idPokemon);
    }

    public List<Integer> obtenerFavoritos(int idUsuario) {
        return favoritoRepository.obtenerIdsPorUsuario(idUsuario);
    }
}
