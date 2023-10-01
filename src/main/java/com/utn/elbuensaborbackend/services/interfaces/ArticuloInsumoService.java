package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.ArticuloInsumoDTO;
import com.utn.elbuensaborbackend.dtos.ArticuloInsumoFullDTO;
import com.utn.elbuensaborbackend.dtos.ArticuloInsumoUpdateDTO;
import com.utn.elbuensaborbackend.entities.ArticuloInsumo;

import java.util.List;

public interface ArticuloInsumoService extends BaseService<ArticuloInsumo, ArticuloInsumoFullDTO, Long> {

    List<ArticuloInsumoDTO> findAllActivos() throws Exception;
    List<ArticuloInsumoDTO> findAllSimple() throws Exception;
    ArticuloInsumoDTO findSimpleById(Long id) throws Exception;
    ArticuloInsumo updateEstado(Long id) throws Exception;
    ArticuloInsumo updateStock(Long id, ArticuloInsumoUpdateDTO dto) throws Exception;
}
