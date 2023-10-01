package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.RolDTO;
import com.utn.elbuensaborbackend.entities.Rol;
import com.utn.elbuensaborbackend.services.interfaces.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/roles")
public class RolController extends BaseControllerImpl<Rol, RolDTO> {

    @Autowired
    private RolService service;
}
