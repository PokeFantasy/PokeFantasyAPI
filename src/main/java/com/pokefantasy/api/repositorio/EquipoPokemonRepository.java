package com.pokefantasy.api.repositorio;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EquipoPokemonRepository {

    private final JdbcTemplate jdbcTemplate;

    public EquipoPokemonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int agregar(int idEquipo, int idPokemon) {
        String sql = "INSERT INTO Equipo_Pokemon (id_equipo, id_pokemon) VALUES (?, ?)";
        return jdbcTemplate.update(sql, idEquipo, idPokemon);
    }

    public int quitar(int idEquipo, int idPokemon) {
        String sql = "DELETE FROM Equipo_Pokemon WHERE id_equipo = ? AND id_pokemon = ?";
        return jdbcTemplate.update(sql, idEquipo, idPokemon);
    }

    public int contar(int idEquipo) {
        String sql = "SELECT COUNT(*) FROM Equipo_Pokemon WHERE id_equipo = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idEquipo);
        return count != null ? count : 0;
    }
}
