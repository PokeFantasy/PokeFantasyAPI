package com.pokefantasy.api.servicio;

import com.pokefantasy.api.modelo.Objeto;
import com.pokefantasy.api.modelo.Pokemon;
import com.pokefantasy.api.repositorio.ObjetoRepository;
import com.pokefantasy.api.repositorio.PokemonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ObjetoService {

    private final ObjetoRepository objetoRepository;
    private final PokemonRepository pokemonRepository;

    public ObjetoService(ObjetoRepository objetoRepository, PokemonRepository pokemonRepository) {
        this.objetoRepository = objetoRepository;
        this.pokemonRepository = pokemonRepository;
    }

    public List<Objeto> obtenerTodos() {
        return objetoRepository.findAll();
    }

    @Transactional
    public boolean aplicarObjeto(int idPokemon, int idObjeto) {
        Objeto objeto = objetoRepository.findById(idObjeto).orElse(null);
        if (objeto == null) {
            return false;
        }

        Pokemon pokemon = pokemonRepository.findById(idPokemon).orElse(null);
        if (pokemon == null) {
            return false;
        }

        switch (objeto.getNombre()) {
            case "Proteína":
                pokemon.setAtaque(pokemon.getAtaque() + 5);
                break;
            case "Hierro":
                pokemon.setDefensa(pokemon.getDefensa() + 5);
                break;
            case "Carbón":
                pokemon.setVelocidad(pokemon.getVelocidad() + 5);
                break;
            case "Caramelo Raro":
                pokemon.setNivel(pokemon.getNivel() + 1);
                break;
            case "Supercaramelo":
                pokemon.setNivel(pokemon.getNivel() + 2);
                break;
            default:
                return false;
        }

        pokemonRepository.save(pokemon);
        objetoRepository.registrarAplicacion(idPokemon, idObjeto);
        return true;
    }
}
