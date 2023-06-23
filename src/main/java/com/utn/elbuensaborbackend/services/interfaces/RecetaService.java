package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.RecetaDTO;
import com.utn.elbuensaborbackend.entities.Receta;

public interface RecetaService extends BaseService<Receta, RecetaDTO, Long> {

    RecetaDTO findByArticuloManufacturadoId(Long id) throws Exception;

}

