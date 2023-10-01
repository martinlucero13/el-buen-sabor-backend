package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.DomicilioDTO;
import com.utn.elbuensaborbackend.entities.Domicilio;
import com.utn.elbuensaborbackend.services.interfaces.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/domicilios")
public class DomicilioController extends BaseControllerImpl<Domicilio, DomicilioDTO> {

    @Autowired
    private DomicilioService service;
}
