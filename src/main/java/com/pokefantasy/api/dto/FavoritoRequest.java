package com.pokefantasy.api.dto;

public class FavoritoRequest {

    private int idUsuario;
    private int idPokemon;

    public FavoritoRequest() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }
}
