package com.pokefantasy.api.repositorio;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoritoRepository {

    private final JdbcTemplate jdbcTemplate;

    public FavoritoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int agregar(int idUsuario, int idPokemon) {
        String sql = "INSERT IGNORE INTO Favorito (id_usuario, id_pokemon) VALUES (?, ?)";
        return jdbcTemplate.update(sql, idUsuario, idPokemon);
    }

    public int quitar(int idUsuario, int idPokemon) {
        String sql = "DELETE FROM Favorito WHERE id_usuario = ? AND id_pokemon = ?";
        return jdbcTemplate.update(sql, idUsuario, idPokemon);
    }

    public boolean existe(int idUsuario, int idPokemon) {
        String sql = "SELECT COUNT(*) FROM Favorito WHERE id_usuario = ? AND id_pokemon = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idUsuario, idPokemon);
        return count != null && count > 0;
    }

    public List<Integer> obtenerIdsPorUsuario(int idUsuario) {
        String sql = "SELECT id_pokemon FROM Favorito WHERE id_usuario = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, idUsuario);
    }
}
