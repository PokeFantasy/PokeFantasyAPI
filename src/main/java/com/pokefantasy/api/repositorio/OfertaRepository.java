package com.pokefantasy.api.repositorio;

import com.pokefantasy.api.modelo.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {

    boolean existsByIdPokemonAndIdOferenteAndEstado(int idPokemon, int idOferente, String estado);

    @Modifying
    @Query("UPDATE Oferta o SET o.estado = :estado WHERE o.idOferta = :idOferta")
    int actualizarEstado(@Param("idOferta") int idOferta, @Param("estado") String estado);

    @Modifying
    @Query("UPDATE Oferta o SET o.estado = 'rechazada' "
         + "WHERE o.idPokemon = :idPokemon AND o.estado = 'pendiente' AND o.idOferta <> :idOfertaExcluir")
    int rechazarOtrasPendientesPorPokemon(@Param("idPokemon") int idPokemon,
                                           @Param("idOfertaExcluir") int idOfertaExcluir);
}
