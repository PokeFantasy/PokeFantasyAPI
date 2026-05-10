package com.pokefantasy.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CrearLigaRequest {

    @NotBlank(message = "el nombre de la liga es obligatorio")
    @Size(max = 100, message = "el nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotNull(message = "idCreador es obligatorio")
    @Positive(message = "idCreador debe ser mayor que 0")
    private Integer idCreador;

    public CrearLigaRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(Integer idCreador) {
        this.idCreador = idCreador;
    }
}
