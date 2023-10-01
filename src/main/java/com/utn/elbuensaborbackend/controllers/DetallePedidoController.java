package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.pedido.DetallePedidoDTO;
import com.utn.elbuensaborbackend.entities.DetallePedido;
import com.utn.elbuensaborbackend.services.interfaces.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/detalles-pedidos")
public class DetallePedidoController extends BaseControllerImpl<DetallePedido, DetallePedidoDTO> {

    @Autowired
    private DetallePedidoService service;

    @GetMapping("/pedido/{id}")
    public ResponseEntity<?> getDetallesByPedidoId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findByPedidoId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/rankingProductos")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public ResponseEntity<?> getArticulosMasPedidos(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findArticulosMasPedidos(fechaInicio, fechaFin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/rankingClientes")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public ResponseEntity<?> getClientesMasPedidos(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findClientesMasPedidos(fechaInicio, fechaFin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/movMonetarios")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public ResponseEntity<?> getInformeMonetarios(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findInformeMonetarios(fechaInicio, fechaFin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PostMapping("/comprar")
    @PreAuthorize("hasAnyAuthority('Cliente')")
    public ResponseEntity<?> saveCompraArticulos(@RequestBody List<DetallePedidoDTO> productos) {
        try {
            service.saveCompraArticulos(productos);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Compra realizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/saveFull")
    @PreAuthorize("hasAuthority('Cliente')")
    public ResponseEntity<?> saveFull(@RequestBody List<DetallePedidoDTO> dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.saveItems(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
