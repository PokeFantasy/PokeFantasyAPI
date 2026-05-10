package com.pokefantasy.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class FavoritoRequest {

    @NotNull(message = "idUsuario es obligatorio")
    @Positive(message = "idUsuario debe ser mayor que 0")
    private Integer idUsuario;

    @NotNull(message = "idPokemon es obligatorio")
    @Positive(message = "idPokemon debe ser mayor que 0")
    private Integer idPokemon;

    public FavoritoRequest() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(Integer idPokemon) {
        this.idPokemon = idPokemon;
    }
}
