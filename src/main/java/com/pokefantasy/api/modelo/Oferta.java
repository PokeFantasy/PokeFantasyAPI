package com.pokefantasy.api.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Oferta")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oferta")
    private int idOferta;

    @Column(name = "id_pokemon", nullable = false)
    private int idPokemon;

    @Column(name = "id_oferente", nullable = false)
    private int idOferente;

    @Column(name = "precio_oferta", nullable = false)
    private double precioOferta;

    @Column(name = "id_propietario", nullable = false)
    private int idPropietario;

    @Column(length = 20)
    private String estado = "pendiente";

    public Oferta() {
    }

    public Oferta(int idPokemon, int idOferente, double precioOferta, int idPropietario) {
        this.idPokemon = idPokemon;
        this.idOferente = idOferente;
        this.precioOferta = precioOferta;
        this.idPropietario = idPropietario;
        this.estado = "pendiente";
    }

    public int getIdOferta() { return idOferta; }
    public void setIdOferta(int idOferta) { this.idOferta = idOferta; }

    public int getIdPokemon() { return idPokemon; }
    public void setIdPokemon(int idPokemon) { this.idPokemon = idPokemon; }

    public int getIdOferente() { return idOferente; }
    public void setIdOferente(int idOferente) { this.idOferente = idOferente; }

    public double getPrecioOferta() { return precioOferta; }
    public void setPrecioOferta(double precioOferta) { this.precioOferta = precioOferta; }

    public int getIdPropietario() { return idPropietario; }
    public void setIdPropietario(int idPropietario) { this.idPropietario = idPropietario; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
