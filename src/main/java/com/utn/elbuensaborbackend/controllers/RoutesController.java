package com.utn.elbuensaborbackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/routes")
public class RoutesController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> adminRouter() {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/cocinero")
    @PreAuthorize("hasAnyAuthority('Cocinero', 'Admin')")
    public ResponseEntity<?> cocineroRouter() {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/cajero")
    @PreAuthorize("hasAnyAuthority('Cajero', 'Admin')")
    public ResponseEntity<?> cajeroRouter() {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/delivery")
    @PreAuthorize("hasAnyAuthority('Delivery', 'Admin')")
    public ResponseEntity<?> deliveryRouter() {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
