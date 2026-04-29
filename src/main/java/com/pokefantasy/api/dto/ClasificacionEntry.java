package com.pokefantasy.api.dto;

public class ClasificacionEntry {

    private int posicion;
    private String nombre;
    private int puntos;

    public ClasificacionEntry() {
    }

    public ClasificacionEntry(int posicion, String nombre, int puntos) {
        this.posicion = posicion;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
