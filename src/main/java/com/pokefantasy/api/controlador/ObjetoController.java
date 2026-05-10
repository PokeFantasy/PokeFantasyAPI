package com.pokefantasy.api.controlador;

import com.pokefantasy.api.dto.AplicarObjetoRequest;
import com.pokefantasy.api.modelo.Objeto;
import com.pokefantasy.api.servicio.ObjetoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/objetos")
public class ObjetoController {

    private final ObjetoService objetoService;

    public ObjetoController(ObjetoService objetoService) {
        this.objetoService = objetoService;
    }

    @GetMapping
    public List<Objeto> obtenerTodos() {
        return objetoService.obtenerTodos();
    }

    @PostMapping("/aplicar")
    public ResponseEntity<String> aplicar(@Valid @RequestBody AplicarObjetoRequest request) {
        boolean ok = objetoService.aplicarObjeto(request.getIdPokemon(), request.getIdObjeto());
        if (ok) {
            return ResponseEntity.ok("Objeto aplicado correctamente");
        }
        return ResponseEntity.badRequest().body("No se pudo aplicar el objeto (id inválido o tipo desconocido)");
    }
}
