package com.pokefantasy.api.repositorio;

import com.pokefantasy.api.modelo.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MercadoRepository extends JpaRepository<Mercado, Integer> {

    boolean existsByIdPokemon(int idPokemon);

    @Modifying
    @Transactional
    int deleteByIdPokemon(int idPokemon);
}
