package com.utn.elbuensaborbackend.controllers;
import com.utn.elbuensaborbackend.dtos.RecetaDTO;
import com.utn.elbuensaborbackend.entities.Receta;
import com.utn.elbuensaborbackend.services.RecetaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/receta")
public class RecetaController {

    @Autowired
    private RecetaServiceImpl service;

    @GetMapping("/byArticuloManufacturado/{id}")
    public ResponseEntity<?> getByArticuloManufacturadoId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findByArticuloManufacturadoId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Receta entity) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.saveReceta(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RecetaDTO entity) {
        try {
            service.updateReceta(id, entity);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("{\"message\":\"Recetao actualizada exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
