package com.pokefantasy.api.dto;

public class CrearOfertaRequest {

    private int idPokemon;
    private int idOferente;
    private double precioOferta;
    private int idPropietario;

    public CrearOfertaRequest() {
    }

    public int getIdPokemon() { return idPokemon; }
    public void setIdPokemon(int idPokemon) { this.idPokemon = idPokemon; }

    public int getIdOferente() { return idOferente; }
    public void setIdOferente(int idOferente) { this.idOferente = idOferente; }

    public double getPrecioOferta() { return precioOferta; }
    public void setPrecioOferta(double precioOferta) { this.precioOferta = precioOferta; }

    public int getIdPropietario() { return idPropietario; }
    public void setIdPropietario(int idPropietario) { this.idPropietario = idPropietario; }
}
