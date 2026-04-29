package com.pokefantasy.api.dto;

public class OfertaRecibidaDTO {

    private int idOferta;
    private String pokemon;
    private int idPokemon;
    private double precioOferta;
    private String oferente;
    private int idOferente;

    public OfertaRecibidaDTO() {
    }

    public OfertaRecibidaDTO(int idOferta, String pokemon, int idPokemon, double precioOferta, String oferente, int idOferente) {
        this.idOferta = idOferta;
        this.pokemon = pokemon;
        this.idPokemon = idPokemon;
        this.precioOferta = precioOferta;
        this.oferente = oferente;
        this.idOferente = idOferente;
    }

    public int getIdOferta() { return idOferta; }
    public void setIdOferta(int idOferta) { this.idOferta = idOferta; }

    public String getPokemon() { return pokemon; }
    public void setPokemon(String pokemon) { this.pokemon = pokemon; }

    public int getIdPokemon() { return idPokemon; }
    public void setIdPokemon(int idPokemon) { this.idPokemon = idPokemon; }

    public double getPrecioOferta() { return precioOferta; }
    public void setPrecioOferta(double precioOferta) { this.precioOferta = precioOferta; }

    public String getOferente() { return oferente; }
    public void setOferente(String oferente) { this.oferente = oferente; }

    public int getIdOferente() { return idOferente; }
    public void setIdOferente(int idOferente) { this.idOferente = idOferente; }
}
