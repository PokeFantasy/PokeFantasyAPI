package com.pokefantasy.api.repositorio;

import com.pokefantasy.api.modelo.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    Optional<Equipo> findByIdUsuario(int idUsuario);
}
