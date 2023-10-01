package com.utn.elbuensaborbackend.controllers;


import com.utn.elbuensaborbackend.dtos.factura.FacturaDTO;
import com.utn.elbuensaborbackend.entities.Factura;
import com.utn.elbuensaborbackend.services.interfaces.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/facturas")
public class FacturaController extends BaseControllerImpl<Factura, FacturaDTO> {

    @Autowired
    private FacturaService service;

    @GetMapping("/byPedido/{id}")
    public ResponseEntity<?> getFacturaByPedidoId(@PathVariable Long id) {
        try {
            FacturaDTO facturaDTO = service.findFacturaByPedidoId(id);
            return ResponseEntity.status(HttpStatus.OK).body(facturaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrió un error\"}");
        }
    }

    @PutMapping("/updateFecha/{id}")
    @PreAuthorize("hasAnyAuthority('Cajero', 'Admin')")
    public ResponseEntity<?> updateFechaBaja(
            @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.updateFecha(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
