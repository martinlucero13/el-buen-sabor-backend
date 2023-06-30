package com.utn.elbuensaborbackend.controllers;


import com.utn.elbuensaborbackend.services.DetalePedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/detalle-pedido")
public class DetallePedidoController{

    @Autowired
    private DetalePedidoServiceImpl service;

    @GetMapping("/byPedido/{id}")
    public ResponseEntity<?> getByPedidoId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findByPedidoId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}


