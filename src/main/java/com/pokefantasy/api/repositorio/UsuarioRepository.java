package com.pokefantasy.api.repositorio;

import com.pokefantasy.api.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmailAndContrasena(String email, String contrasena);

    boolean existsByEmail(String email);

    @Modifying
    @Query(value = "UPDATE Usuario SET saldo = saldo + :delta WHERE id_usuario = :idUsuario",
           nativeQuery = true)
    int ajustarSaldo(@Param("idUsuario") int idUsuario, @Param("delta") double delta);
}
