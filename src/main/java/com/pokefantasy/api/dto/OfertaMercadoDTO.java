package com.pokefantasy.api.dto;

public class OfertaMercadoDTO {

    private int idMercado;
    private int idPokemon;
    private String nombre;
    private int ataque;
    private int defensa;
    private int velocidad;
    private int nivel;
    private double precio;
    private String vendedor;
    private int idVendedor;

    public OfertaMercadoDTO() {
    }

    public OfertaMercadoDTO(int idMercado, int idPokemon, String nombre, int ataque,
                            int defensa, int velocidad, int nivel, double precio,
                            String vendedor, int idVendedor) {
        this.idMercado = idMercado;
        this.idPokemon = idPokemon;
        this.nombre = nombre;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.nivel = nivel;
        this.precio = precio;
        this.vendedor = vendedor;
        this.idVendedor = idVendedor;
    }

    public int getIdMercado() { return idMercado; }
    public void setIdMercado(int idMercado) { this.idMercado = idMercado; }

    public int getIdPokemon() { return idPokemon; }
    public void setIdPokemon(int idPokemon) { this.idPokemon = idPokemon; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }

    public int getDefensa() { return defensa; }
    public void setDefensa(int defensa) { this.defensa = defensa; }

    public int getVelocidad() { return velocidad; }
    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getVendedor() { return vendedor; }
    public void setVendedor(String vendedor) { this.vendedor = vendedor; }

    public int getIdVendedor() { return idVendedor; }
    public void setIdVendedor(int idVendedor) { this.idVendedor = idVendedor; }
}
