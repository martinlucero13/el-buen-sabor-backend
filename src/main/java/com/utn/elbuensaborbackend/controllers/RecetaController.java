package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.RecetaDTO;
import com.utn.elbuensaborbackend.entities.Receta;
import com.utn.elbuensaborbackend.services.interfaces.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "api/v1/recetas")
public class RecetaController extends BaseControllerImpl<Receta, RecetaDTO> {

    @Autowired
    private RecetaService service;

    @GetMapping("/byArticuloManufacturado/{id}")
    ResponseEntity<?> getByArticuloManufacturadoId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findByArticuloManufacturadoId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
