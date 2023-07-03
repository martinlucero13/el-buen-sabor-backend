package com.utn.elbuensaborbackend.services.interfaces;


import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoDTO;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;

import java.util.List;


public interface ArticuloManufacturadoService {
    List<ArticuloManufacturadoDTO> findAll() throws Exception;

    ArticuloManufacturadoDTO findById(Long id) throws  Exception;

    ArticuloManufacturadoDTO findWithReceta(Long id) throws  Exception;

    List<ArticuloManufacturadoDTO> findByTermino(String termino) throws Exception;

    ArticuloManufacturado save(ArticuloManufacturadoDTO entity) throws Exception;

    ArticuloManufacturado update(Long id, ArticuloManufacturadoDTO entity) throws Exception;

    void delete(Long id) throws Exception;
}
