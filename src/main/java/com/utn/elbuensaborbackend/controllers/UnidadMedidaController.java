package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.RubroDTO;
import com.utn.elbuensaborbackend.dtos.UnidadMedidaDTO;
import com.utn.elbuensaborbackend.entities.UnidadMedida;

import com.utn.elbuensaborbackend.services.UnidadMedidaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/unidad-medida")
public class UnidadMedidaController  extends BaseControllerImpl<UnidadMedida, UnidadMedidaDTO>{

    @Autowired
    private UnidadMedidaServiceImpl service;


    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody UnidadMedidaDTO entity) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UnidadMedidaDTO entity) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
