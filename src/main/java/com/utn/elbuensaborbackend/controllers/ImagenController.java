package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.ImagenDTO;
import com.utn.elbuensaborbackend.entities.Imagen;
import com.utn.elbuensaborbackend.services.interfaces.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/imagenes")
public class ImagenController extends BaseControllerImpl<Imagen, ImagenDTO> {

    @Autowired
    private ImagenService service;

    @GetMapping("/byName/{nombre}")
    public ResponseEntity<?> getByName(@PathVariable String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(service.findByName(nombre));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
