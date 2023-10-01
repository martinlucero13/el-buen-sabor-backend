package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.pedido.PedidoDTO;
import com.utn.elbuensaborbackend.entities.Pedido;
import com.utn.elbuensaborbackend.enums.EstadoPedido;
import com.utn.elbuensaborbackend.services.interfaces.FacturaService;
import com.utn.elbuensaborbackend.services.interfaces.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoDTO> {

    @Autowired
    private PedidoService service;

    @Autowired
    private FacturaService serviceFactura;

    @GetMapping("/filtrados")
    public ResponseEntity<?> getAllByEstado(@RequestParam(name = "estado", required = false) EstadoPedido estado) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAllByEstado(estado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/byCliente/{id}")
    public ResponseEntity<?> getAllByCliente(@PathVariable Long id) {
        try {
            List<PedidoDTO> pedidos = service.findAllByCliente(id);
            return ResponseEntity.status(HttpStatus.OK).body(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrió un error\"}");
        }
    }

    @GetMapping("/tiempoCocina")
    public ResponseEntity<?> getTiempoCocina() {
        try {
            int totalMinutos = service.findTiempoCocina();
            return ResponseEntity.status(HttpStatus.OK).body(totalMinutos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/saveFull")
    @PreAuthorize("hasAuthority('Cliente')")
    public ResponseEntity<?> saveFull(@RequestBody PedidoDTO pedidoDTO) {
        try {
            Pedido pedido = service.save(pedidoDTO);
            serviceFactura.saveFactura(pedido, pedidoDTO.getFactura());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(pedido.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/updateEstado/{id}")
    @PreAuthorize("hasAnyAuthority('Cajero', 'Cocinero', 'Delivery', 'Admin')")
    public ResponseEntity<?> updateEstado(
            @PathVariable Long id,
            @RequestParam(name = "estado") EstadoPedido estado) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.updateEstado(id, estado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/updateTiempo/{id}")
    @PreAuthorize("hasAnyAuthority('Cocinero', 'Admin')")
    public ResponseEntity<?> updateTiempo(
            @PathVariable Long id,
            @RequestParam(name = "tiempo") String tiempo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.updateTiempo(id, tiempo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/cancelarPedido/{id}")
    public ResponseEntity<?> updateStock(@PathVariable Long id) {
        try {
            service.updateStock(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Pedido cancelado y stock restaurado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}