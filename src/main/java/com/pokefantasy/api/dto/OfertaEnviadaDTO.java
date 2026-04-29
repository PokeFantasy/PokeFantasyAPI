package com.pokefantasy.api.dto;

public class OfertaEnviadaDTO {

    private int idOferta;
    private String pokemon;
    private double precioOferta;
    private String propietario;
    private String estado;

    public OfertaEnviadaDTO() {
    }

    public OfertaEnviadaDTO(int idOferta, String pokemon, double precioOferta, String propietario, String estado) {
        this.idOferta = idOferta;
        this.pokemon = pokemon;
        this.precioOferta = precioOferta;
        this.propietario = propietario;
        this.estado = estado;
    }

    public int getIdOferta() { return idOferta; }
    public void setIdOferta(int idOferta) { this.idOferta = idOferta; }

    public String getPokemon() { return pokemon; }
    public void setPokemon(String pokemon) { this.pokemon = pokemon; }

    public double getPrecioOferta() { return precioOferta; }
    public void setPrecioOferta(double precioOferta) { this.precioOferta = precioOferta; }

    public String getPropietario() { return propietario; }
    public void setPropietario(String propietario) { this.propietario = propietario; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
