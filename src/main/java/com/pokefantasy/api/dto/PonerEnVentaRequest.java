package com.pokefantasy.api.dto;

public class PonerEnVentaRequest {

    private int idPokemon;
    private double precio;
    private int idVendedor;

    public PonerEnVentaRequest() {
    }

    public int getIdPokemon() { return idPokemon; }
    public void setIdPokemon(int idPokemon) { this.idPokemon = idPokemon; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getIdVendedor() { return idVendedor; }
    public void setIdVendedor(int idVendedor) { this.idVendedor = idVendedor; }
}
