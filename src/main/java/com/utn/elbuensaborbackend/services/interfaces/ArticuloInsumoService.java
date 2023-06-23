package com.utn.elbuensaborbackend.services.interfaces;
import com.utn.elbuensaborbackend.dtos.ArticuloInsumoDTO;
import com.utn.elbuensaborbackend.entities.ArticuloInsumo;

import java.util.List;


public interface ArticuloInsumoService {
    List<ArticuloInsumoDTO> findAll() throws Exception;

    List<ArticuloInsumoDTO> findBebidas() throws Exception;

    ArticuloInsumoDTO findById(Long id) throws  Exception;

    ArticuloInsumo save(ArticuloInsumoDTO entity) throws Exception;

    ArticuloInsumo update(Long id, ArticuloInsumoDTO entity) throws Exception;

    void delete(Long id) throws Exception;
}
