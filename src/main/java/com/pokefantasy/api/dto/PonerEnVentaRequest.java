package com.pokefantasy.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PonerEnVentaRequest {

    @NotNull(message = "idPokemon es obligatorio")
    @Positive(message = "idPokemon debe ser mayor que 0")
    private Integer idPokemon;

    @NotNull(message = "precio es obligatorio")
    @Positive(message = "precio debe ser mayor que 0")
    private Double precio;

    @NotNull(message = "idVendedor es obligatorio")
    @Positive(message = "idVendedor debe ser mayor que 0")
    private Integer idVendedor;

    public PonerEnVentaRequest() {
    }

    public Integer getIdPokemon() { return idPokemon; }
    public void setIdPokemon(Integer idPokemon) { this.idPokemon = idPokemon; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getIdVendedor() { return idVendedor; }
    public void setIdVendedor(Integer idVendedor) { this.idVendedor = idVendedor; }
}
