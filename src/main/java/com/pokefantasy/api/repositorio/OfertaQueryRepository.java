package com.pokefantasy.api.repositorio;

import com.pokefantasy.api.dto.HistorialOfertaDTO;
import com.pokefantasy.api.dto.OfertaEnviadaDTO;
import com.pokefantasy.api.dto.OfertaRecibidaDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfertaQueryRepository {

    private final JdbcTemplate jdbcTemplate;

    public OfertaQueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OfertaEnviadaDTO> obtenerEnviadas(int idOferente) {
        String sql = "SELECT o.id_oferta, p.nombre, o.precio_oferta, u.nombre AS propietario, o.estado "
                   + "FROM Oferta o "
                   + "INNER JOIN Pokemon p ON o.id_pokemon = p.id_pokemon "
                   + "INNER JOIN Usuario u ON o.id_propietario = u.id_usuario "
                   + "WHERE o.id_oferente = ? AND o.estado = 'pendiente' "
                   + "ORDER BY o.id_oferta DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new OfertaEnviadaDTO(
                rs.getInt("id_oferta"),
                rs.getString("nombre"),
                rs.getDouble("precio_oferta"),
                rs.getString("propietario"),
                rs.getString("estado")
        ), idOferente);
    }

    public List<OfertaRecibidaDTO> obtenerRecibidas(int idPropietario) {
        String sql = "SELECT o.id_oferta, p.nombre, p.id_pokemon, o.precio_oferta, "
                   + "u.nombre AS oferente, o.id_oferente "
                   + "FROM Oferta o "
                   + "INNER JOIN Pokemon p ON o.id_pokemon = p.id_pokemon "
                   + "INNER JOIN Usuario u ON o.id_oferente = u.id_usuario "
                   + "WHERE o.id_propietario = ? AND o.estado = 'pendiente' "
                   + "ORDER BY o.id_oferta DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new OfertaRecibidaDTO(
                rs.getInt("id_oferta"),
                rs.getString("nombre"),
                rs.getInt("id_pokemon"),
                rs.getDouble("precio_oferta"),
                rs.getString("oferente"),
                rs.getInt("id_oferente")
        ), idPropietario);
    }

    public List<HistorialOfertaDTO> obtenerHistorial(int idUsuario) {
        String sql = "SELECT o.id_oferta, p.nombre, o.precio_oferta, "
                   + "CASE WHEN o.id_oferente = ? THEN 'Enviada' ELSE 'Recibida' END AS direccion, "
                   + "CASE WHEN o.id_oferente = ? THEN u2.nombre ELSE u1.nombre END AS otro_usuario, "
                   + "o.estado "
                   + "FROM Oferta o "
                   + "INNER JOIN Pokemon p ON o.id_pokemon = p.id_pokemon "
                   + "INNER JOIN Usuario u1 ON o.id_oferente = u1.id_usuario "
                   + "INNER JOIN Usuario u2 ON o.id_propietario = u2.id_usuario "
                   + "WHERE (o.id_oferente = ? OR o.id_propietario = ?) "
                   + "AND o.estado != 'pendiente' "
                   + "ORDER BY o.id_oferta DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new HistorialOfertaDTO(
                rs.getInt("id_oferta"),
                rs.getString("nombre"),
                rs.getDouble("precio_oferta"),
                rs.getString("direccion"),
                rs.getString("otro_usuario"),
                rs.getString("estado")
        ), idUsuario, idUsuario, idUsuario, idUsuario);
    }
}
