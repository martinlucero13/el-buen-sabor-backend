package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.LocalidadDTO;
import com.utn.elbuensaborbackend.entities.Localidad;
import com.utn.elbuensaborbackend.services.interfaces.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/localidades")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadDTO> {

    @Autowired
    private LocalidadService service;
}
