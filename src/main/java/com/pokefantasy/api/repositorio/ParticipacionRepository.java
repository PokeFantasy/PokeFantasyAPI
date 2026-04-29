package com.pokefantasy.api.repositorio;

import com.pokefantasy.api.dto.ClasificacionEntry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParticipacionRepository {

    private final JdbcTemplate jdbcTemplate;

    public ParticipacionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int unirse(int idUsuario, int idLiga) {
        String sql = "INSERT INTO Participacion (id_usuario, id_liga, puntos) VALUES (?, ?, 0)";
        return jdbcTemplate.update(sql, idUsuario, idLiga);
    }

    public int salir(int idUsuario, int idLiga) {
        String sql = "DELETE FROM Participacion WHERE id_usuario = ? AND id_liga = ?";
        return jdbcTemplate.update(sql, idUsuario, idLiga);
    }

    public int contarParticipantes(int idLiga) {
        String sql = "SELECT COUNT(*) FROM Participacion WHERE id_liga = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idLiga);
        return count != null ? count : 0;
    }

    public int sumarPuntos(int idUsuario, int idLiga, int puntos) {
        String sql = "UPDATE Participacion SET puntos = puntos + ? WHERE id_usuario = ? AND id_liga = ?";
        return jdbcTemplate.update(sql, puntos, idUsuario, idLiga);
    }

    public List<ClasificacionEntry> obtenerClasificacion(int idLiga) {
        String sql = "SELECT u.nombre, p.puntos FROM Participacion p "
                   + "INNER JOIN Usuario u ON p.id_usuario = u.id_usuario "
                   + "WHERE p.id_liga = ? ORDER BY p.puntos DESC";
        List<ClasificacionEntry> lista = new ArrayList<>();
        int[] posicion = {1};
        jdbcTemplate.query(sql, rs -> {
            lista.add(new ClasificacionEntry(posicion[0]++, rs.getString("nombre"), rs.getInt("puntos")));
        }, idLiga);
        return lista;
    }

    public List<Integer> obtenerParticipantes(int idLiga) {
        String sql = "SELECT id_usuario FROM Participacion WHERE id_liga = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, idLiga);
    }
}
