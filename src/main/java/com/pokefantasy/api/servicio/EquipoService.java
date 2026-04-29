package com.pokefantasy.api.servicio;

import com.pokefantasy.api.modelo.Equipo;
import com.pokefantasy.api.repositorio.EquipoPokemonRepository;
import com.pokefantasy.api.repositorio.EquipoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final EquipoPokemonRepository equipoPokemonRepository;

    public EquipoService(EquipoRepository equipoRepository,
                         EquipoPokemonRepository equipoPokemonRepository) {
        this.equipoRepository = equipoRepository;
        this.equipoPokemonRepository = equipoPokemonRepository;
    }

    @Transactional
    public Equipo obtenerOCrearEquipo(int idUsuario) {
        return equipoRepository.findByIdUsuario(idUsuario)
                .orElseGet(() -> equipoRepository.save(new Equipo(idUsuario)));
    }

    public boolean agregarPokemon(int idEquipo, int idPokemon) {
        return equipoPokemonRepository.agregar(idEquipo, idPokemon) > 0;
    }

    public boolean quitarPokemon(int idEquipo, int idPokemon) {
        return equipoPokemonRepository.quitar(idEquipo, idPokemon) > 0;
    }

    public int contarPokemon(int idEquipo) {
        return equipoPokemonRepository.contar(idEquipo);
    }
}
