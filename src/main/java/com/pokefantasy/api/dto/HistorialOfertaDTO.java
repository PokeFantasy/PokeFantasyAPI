package com.pokefantasy.api.dto;

public class HistorialOfertaDTO {

    private int idOferta;
    private String pokemon;
    private double precioOferta;
    private String direccion;
    private String otroUsuario;
    private String estado;

    public HistorialOfertaDTO() {
    }

    public HistorialOfertaDTO(int idOferta, String pokemon, double precioOferta, String direccion, String otroUsuario, String estado) {
        this.idOferta = idOferta;
        this.pokemon = pokemon;
        this.precioOferta = precioOferta;
        this.direccion = direccion;
        this.otroUsuario = otroUsuario;
        this.estado = estado;
    }

    public int getIdOferta() { return idOferta; }
    public void setIdOferta(int idOferta) { this.idOferta = idOferta; }

    public String getPokemon() { return pokemon; }
    public void setPokemon(String pokemon) { this.pokemon = pokemon; }

    public double getPrecioOferta() { return precioOferta; }
    public void setPrecioOferta(double precioOferta) { this.precioOferta = precioOferta; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getOtroUsuario() { return otroUsuario; }
    public void setOtroUsuario(String otroUsuario) { this.otroUsuario = otroUsuario; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
