package com.utn.elbuensaborbackend.controllers;/*package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.pedido.ArticuloCantidadDTO;
import com.utn.elbuensaborbackend.services.interfaces.MercadoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/mercado-pago")
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService service;

    @PostMapping(value = "/createPreference")
    public ResponseEntity<?> createPreference(@RequestBody List<ArticuloCantidadDTO> detallesPedido,
                                              @RequestParam String estadoEntrega) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.createPreference(detallesPedido, estadoEntrega));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
*/