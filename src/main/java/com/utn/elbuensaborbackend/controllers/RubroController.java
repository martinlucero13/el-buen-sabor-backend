package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.RubroDTO;
import com.utn.elbuensaborbackend.entities.Rubro;
import com.utn.elbuensaborbackend.services.interfaces.RubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/rubros")
public class RubroController extends BaseControllerImpl<Rubro, RubroDTO> {

    @Autowired
    private RubroService service;

    @Override
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/activos")
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> getAllActivos() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAllActivos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/byTipo/{bool}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> getByTipo(@PathVariable Boolean bool) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findByTipo(bool));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/exists/{denominacion}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> existsByDenominacion(@PathVariable String denominacion) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.existsByDenominacion(denominacion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/updateEstado/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> updateEstado(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(service.updateEstado(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
