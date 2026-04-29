package com.pokefantasy.api.controlador;

import com.pokefantasy.api.dto.ComprarRequest;
import com.pokefantasy.api.dto.OfertaMercadoDTO;
import com.pokefantasy.api.dto.PonerEnVentaRequest;
import com.pokefantasy.api.modelo.Mercado;
import com.pokefantasy.api.servicio.MercadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mercado")
public class MercadoController {

    private final MercadoService mercadoService;

    public MercadoController(MercadoService mercadoService) {
        this.mercadoService = mercadoService;
    }

    @GetMapping
    public List<OfertaMercadoDTO> obtenerOfertas() {
        return mercadoService.obtenerOfertas();
    }

    @PostMapping
    public Mercado ponerEnVenta(@RequestBody PonerEnVentaRequest request) {
        return mercadoService.ponerEnVenta(request.getIdPokemon(), request.getPrecio(), request.getIdVendedor());
    }

    @PostMapping("/{idMercado}/comprar")
    public ResponseEntity<String> comprar(@PathVariable int idMercado,
                                           @RequestBody ComprarRequest request) {
        if (mercadoService.comprar(idMercado, request.getIdComprador())) {
            return ResponseEntity.ok("Compra realizada");
        }
        return ResponseEntity.badRequest().body("No se pudo realizar la compra (saldo insuficiente o id inválido)");
    }

    @GetMapping("/pokemon/{idPokemon}")
    public boolean estaEnVenta(@PathVariable int idPokemon) {
        return mercadoService.estaEnVenta(idPokemon);
    }
}
