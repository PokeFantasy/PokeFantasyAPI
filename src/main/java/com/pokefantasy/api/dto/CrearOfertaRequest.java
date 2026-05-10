package com.pokefantasy.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CrearOfertaRequest {

    @NotNull(message = "idPokemon es obligatorio")
    @Positive(message = "idPokemon debe ser mayor que 0")
    private Integer idPokemon;

    @NotNull(message = "idOferente es obligatorio")
    @Positive(message = "idOferente debe ser mayor que 0")
    private Integer idOferente;

    @NotNull(message = "precioOferta es obligatorio")
    @Positive(message = "precioOferta debe ser mayor que 0")
    private Double precioOferta;

    @NotNull(message = "idPropietario es obligatorio")
    @Positive(message = "idPropietario debe ser mayor que 0")
    private Integer idPropietario;

    public CrearOfertaRequest() {
    }

    public Integer getIdPokemon() { return idPokemon; }
    public void setIdPokemon(Integer idPokemon) { this.idPokemon = idPokemon; }

    public Integer getIdOferente() { return idOferente; }
    public void setIdOferente(Integer idOferente) { this.idOferente = idOferente; }

    public Double getPrecioOferta() { return precioOferta; }
    public void setPrecioOferta(Double precioOferta) { this.precioOferta = precioOferta; }

    public Integer getIdPropietario() { return idPropietario; }
    public void setIdPropietario(Integer idPropietario) { this.idPropietario = idPropietario; }
}
