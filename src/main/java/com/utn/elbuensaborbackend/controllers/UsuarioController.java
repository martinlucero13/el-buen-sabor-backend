package com.utn.elbuensaborbackend.controllers;

import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonPrimitive;
import com.utn.elbuensaborbackend.dtos.UsuarioDTO;
import com.utn.elbuensaborbackend.entities.Usuario;
import com.utn.elbuensaborbackend.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioDTO> {

    @Autowired
    private UsuarioService service;

    @GetMapping("/exists/{email}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> existsByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.existsByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
