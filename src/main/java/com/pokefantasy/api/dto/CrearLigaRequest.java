package com.pokefantasy.api.dto;

public class CrearLigaRequest {

    private String nombre;
    private int idCreador;

    public CrearLigaRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(int idCreador) {
        this.idCreador = idCreador;
    }
}
