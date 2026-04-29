package com.pokefantasy.api.repositorio;

import com.pokefantasy.api.modelo.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    @Query(value = "SELECT p.* FROM Pokemon p "
                 + "INNER JOIN Equipo_Pokemon ep ON p.id_pokemon = ep.id_pokemon "
                 + "INNER JOIN Equipo e ON ep.id_equipo = e.id_equipo "
                 + "WHERE e.id_usuario = :idUsuario",
           nativeQuery = true)
    List<Pokemon> findByEquipoUsuario(@Param("idUsuario") int idUsuario);

    @Query(value = "SELECT p.* FROM Pokemon p "
                 + "INNER JOIN Mercado m ON p.id_pokemon = m.id_pokemon",
           nativeQuery = true)
    List<Pokemon> findDisponiblesEnMercado();
}
