package com.utn.elbuensaborbackend.services.interfaces;


import com.utn.elbuensaborbackend.dtos.ArticuloInsumoDTO;
import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoInsumoDTO;
import com.utn.elbuensaborbackend.entities.ArticuloInsumo;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturadoInsumo;

import java.util.List;


public interface ArticuloManufacturadoInsumoService{
    List<ArticuloManufacturadoInsumoDTO> findByArticuloManufacturadoId(Long articuloManufacturadoId) throws Exception;

    List<ArticuloManufacturadoInsumoDTO> findAll() throws Exception;

    ArticuloManufacturadoInsumoDTO findById(Long id) throws  Exception;

    ArticuloManufacturadoInsumo save(ArticuloManufacturadoInsumoDTO entity) throws Exception;

    void update(Long id, ArticuloManufacturadoInsumoDTO entity) throws Exception;

    void delete(Long id) throws Exception;
}
