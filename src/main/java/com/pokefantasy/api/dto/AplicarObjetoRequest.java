package com.pokefantasy.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AplicarObjetoRequest {

    @NotNull(message = "idPokemon es obligatorio")
    @Positive(message = "idPokemon debe ser mayor que 0")
    private Integer idPokemon;

    @NotNull(message = "idObjeto es obligatorio")
    @Positive(message = "idObjeto debe ser mayor que 0")
    private Integer idObjeto;

    public AplicarObjetoRequest() {
    }

    public Integer getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(Integer idPokemon) {
        this.idPokemon = idPokemon;
    }

    public Integer getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Integer idObjeto) {
        this.idObjeto = idObjeto;
    }
}
