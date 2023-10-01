package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.ClienteDTO;
import com.utn.elbuensaborbackend.entities.Cliente;
import com.utn.elbuensaborbackend.services.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController extends BaseControllerImpl<Cliente, ClienteDTO> {

    @Autowired
    private ClienteService service;

    @Override
    @GetMapping("")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/rolEmpleados")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getAllEmpleados() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAllEmpleados());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/byPedido/{id}")
    public ResponseEntity<?> getClienteByPedido(@PathVariable Long id) {
        try {
            ClienteDTO clienteDTO = service.findClienteByPedido(id);
                return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrió un error\"}");
        }
    }

    @GetMapping("/rolClientes")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getAllClientes() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAllClientes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/byAuth0Id")
    public ResponseEntity<?> getByUsuarioAuth0Id(@RequestParam String auth0Id) {
        try {
            auth0Id = URLDecoder.decode(auth0Id, StandardCharsets.UTF_8);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findClienteByUsuarioAuth0Id(auth0Id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/updateEstado/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> updateEstado(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.updateEstado(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
