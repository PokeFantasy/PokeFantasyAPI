package com.pokefantasy.api.repositorio;

import com.pokefantasy.api.dto.OfertaMercadoDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MercadoQueryRepository {

    private final JdbcTemplate jdbcTemplate;

    public MercadoQueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OfertaMercadoDTO> obtenerOfertas() {
        String sql = "SELECT m.id_mercado, p.id_pokemon, p.nombre, p.ataque, p.defensa, "
                   + "p.velocidad, p.nivel, m.precio, u.nombre AS vendedor, m.id_vendedor "
                   + "FROM Mercado m "
                   + "INNER JOIN Pokemon p ON m.id_pokemon = p.id_pokemon "
                   + "INNER JOIN Usuario u ON m.id_vendedor = u.id_usuario";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new OfertaMercadoDTO(
                rs.getInt("id_mercado"),
                rs.getInt("id_pokemon"),
                rs.getString("nombre"),
                rs.getInt("ataque"),
                rs.getInt("defensa"),
                rs.getInt("velocidad"),
                rs.getInt("nivel"),
                rs.getDouble("precio"),
                rs.getString("vendedor"),
                rs.getInt("id_vendedor")
        ));
    }
}
