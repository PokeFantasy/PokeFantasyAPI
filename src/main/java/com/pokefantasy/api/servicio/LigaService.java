package com.pokefantasy.api.servicio;

import com.pokefantasy.api.dto.ClasificacionEntry;
import com.pokefantasy.api.modelo.Liga;
import com.pokefantasy.api.repositorio.LigaRepository;
import com.pokefantasy.api.repositorio.ParticipacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LigaService {

    private final LigaRepository ligaRepository;
    private final ParticipacionRepository participacionRepository;

    public LigaService(LigaRepository ligaRepository,
                       ParticipacionRepository participacionRepository) {
        this.ligaRepository = ligaRepository;
        this.participacionRepository = participacionRepository;
    }

    @Transactional
    public Liga crearLiga(String nombre, int idCreador) {
        Liga liga = ligaRepository.save(new Liga(nombre));
        participacionRepository.unirse(idCreador, liga.getIdLiga());
        return liga;
    }

    public List<Liga> obtenerDisponibles(int idUsuario) {
        return ligaRepository.findDisponiblesParaUsuario(idUsuario);
    }

    public List<Liga> obtenerLigasDeUsuario(int idUsuario) {
        return ligaRepository.findByUsuarioParticipante(idUsuario);
    }

    public boolean unirse(int idUsuario, int idLiga) {
        return participacionRepository.unirse(idUsuario, idLiga) > 0;
    }

    public List<ClasificacionEntry> obtenerClasificacion(int idLiga) {
        return participacionRepository.obtenerClasificacion(idLiga);
    }

    public void sumarPuntos(int idUsuario, int idLiga, int puntos) {
        participacionRepository.sumarPuntos(idUsuario, idLiga, puntos);
    }

    @Transactional
    public boolean salirDeLiga(int idUsuario, int idLiga) {
        int eliminadas = participacionRepository.salir(idUsuario, idLiga);
        if (eliminadas == 0) {
            return false;
        }
        if (participacionRepository.contarParticipantes(idLiga) == 0) {
            ligaRepository.deleteById(idLiga);
        }
        return true;
    }

    public List<Integer> obtenerParticipantes(int idLiga) {
        return participacionRepository.obtenerParticipantes(idLiga);
    }
}
