package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.ArticuloInsumoFullDTO;
import com.utn.elbuensaborbackend.dtos.ArticuloInsumoUpdateDTO;
import com.utn.elbuensaborbackend.entities.ArticuloInsumo;
import com.utn.elbuensaborbackend.services.interfaces.ArticuloInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/articulos-insumos")
public class ArticuloInsumoController extends BaseControllerImpl<ArticuloInsumo, ArticuloInsumoFullDTO> {

    @Autowired
    private ArticuloInsumoService service;

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

    @GetMapping("/simple")
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> getAllSimple() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAllSimple());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/simple/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> getSimpleById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findSimpleById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/updateEstado/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> updateEstado(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.updateEstado(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/updateStock/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestBody ArticuloInsumoUpdateDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.updateStock(id, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
