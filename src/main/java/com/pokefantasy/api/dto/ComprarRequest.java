package com.pokefantasy.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ComprarRequest {

    @NotNull(message = "idComprador es obligatorio")
    @Positive(message = "idComprador debe ser mayor que 0")
    private Integer idComprador;

    public ComprarRequest() {
    }

    public Integer getIdComprador() { return idComprador; }
    public void setIdComprador(Integer idComprador) { this.idComprador = idComprador; }
}
