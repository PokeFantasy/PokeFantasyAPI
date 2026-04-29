package com.pokefantasy.api.dto;

public class AplicarObjetoRequest {

    private int idPokemon;
    private int idObjeto;

    public AplicarObjetoRequest() {
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }
}
