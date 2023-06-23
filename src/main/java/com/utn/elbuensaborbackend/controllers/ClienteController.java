package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.ClienteDTO;
import com.utn.elbuensaborbackend.entities.Cliente;
import com.utn.elbuensaborbackend.services.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController extends BaseControllerImpl<Cliente, ClienteDTO> {

    @Autowired
    private ClienteServiceImpl service;

    @GetMapping("/byRoles/{roles}")
    public ResponseEntity<?> getAllClientesByRoles(@PathVariable List<String> roles) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAllClientesByRoles(roles));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/byNombre/{nombre}")
    public ResponseEntity<?> getAllClientesByName(@PathVariable String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAllClientesByName(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/byApellido/{apellido}")
    public ResponseEntity<?> getAllClientesByApellido(@PathVariable String apellido) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAllClientesByApellido(apellido));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/byNombreAndApellido/{nombre}/{apellido}")
    public ResponseEntity<?> getAllClientesByNombreAndApellido(@PathVariable String nombre, @PathVariable String apellido) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAllClientesByNameAndApellido(nombre, apellido));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ClienteDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.saveCliente(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.updateCliente(id, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
