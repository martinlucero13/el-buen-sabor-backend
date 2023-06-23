package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.LocalidadDTO;
import com.utn.elbuensaborbackend.entities.Localidad;
import com.utn.elbuensaborbackend.services.LocalidadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/localidades")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadDTO> {

    @Autowired
    private LocalidadServiceImpl service;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody LocalidadDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.save(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> save(@PathVariable Long id, @RequestBody LocalidadDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
