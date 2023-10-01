package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.DetalleArticuloManufacturadoDTO;
import com.utn.elbuensaborbackend.entities.DetalleArticuloManufacturado;
import com.utn.elbuensaborbackend.services.interfaces.DetalleArticuloManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/detalles-articulos-manufacturados")
public class DetalleArticuloManufacturadoController extends BaseControllerImpl<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDTO> {

    @Autowired
    private DetalleArticuloManufacturadoService service;
}
