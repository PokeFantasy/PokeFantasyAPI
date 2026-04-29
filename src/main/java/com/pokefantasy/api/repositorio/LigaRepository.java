package com.pokefantasy.api.repositorio;

import com.pokefantasy.api.modelo.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigaRepository extends JpaRepository<Liga, Integer> {

    @Query(value = "SELECT * FROM Liga WHERE estado = 'activa' "
                 + "AND id_liga NOT IN (SELECT id_liga FROM Participacion WHERE id_usuario = :idUsuario)",
           nativeQuery = true)
    List<Liga> findDisponiblesParaUsuario(@Param("idUsuario") int idUsuario);

    @Query(value = "SELECT l.* FROM Liga l "
                 + "INNER JOIN Participacion p ON l.id_liga = p.id_liga "
                 + "WHERE p.id_usuario = :idUsuario",
           nativeQuery = true)
    List<Liga> findByUsuarioParticipante(@Param("idUsuario") int idUsuario);
}
