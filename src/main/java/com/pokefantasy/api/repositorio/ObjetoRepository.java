package com.pokefantasy.api.repositorio;

import com.pokefantasy.api.modelo.Objeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetoRepository extends JpaRepository<Objeto, Integer> {

    @Modifying
    @Query(value = "INSERT INTO Pokemon_Objeto (id_pokemon, id_objeto) VALUES (:idPokemon, :idObjeto) "
                 + "ON DUPLICATE KEY UPDATE id_objeto = id_objeto",
           nativeQuery = true)
    void registrarAplicacion(@Param("idPokemon") int idPokemon, @Param("idObjeto") int idObjeto);
}
