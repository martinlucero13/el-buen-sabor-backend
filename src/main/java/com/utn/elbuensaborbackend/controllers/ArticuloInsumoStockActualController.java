package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.ArticuloInsumoStockActualDTO;
import com.utn.elbuensaborbackend.dtos.RecetaDTO;
import com.utn.elbuensaborbackend.services.ArticuloInsumoStockActualServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/articulos-insumos-stock-actual")
public class ArticuloInsumoStockActualController {

    @Autowired
    private ArticuloInsumoStockActualServiceImpl service;

    @PutMapping("/{id}")
    public ResponseEntity<?> comprar(@PathVariable Long id, @RequestBody ArticuloInsumoStockActualDTO entity) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.comprar(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
