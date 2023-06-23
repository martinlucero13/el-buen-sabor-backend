package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.DomicilioDTO;
import com.utn.elbuensaborbackend.entities.Domicilio;
import com.utn.elbuensaborbackend.services.DomicilioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/domicilios")
public class DomicilioController extends BaseControllerImpl<Domicilio, DomicilioDTO> {

    @Autowired
    private DomicilioServiceImpl service;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody DomicilioDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.saveDomicilio(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody DomicilioDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.updateDomicilio(id, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
