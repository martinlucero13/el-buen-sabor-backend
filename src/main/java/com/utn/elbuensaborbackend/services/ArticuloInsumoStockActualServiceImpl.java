package com.utn.elbuensaborbackend.services;


import com.utn.elbuensaborbackend.dtos.ArticuloInsumoStockActualDTO;
import com.utn.elbuensaborbackend.entities.ArticuloInsumoStockActual;
import com.utn.elbuensaborbackend.repositories.ArticuloInsumoStockActualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticuloInsumoStockActualServiceImpl {

    @Autowired
    private ArticuloInsumoStockActualRepository articuloInsumoStockActualRepository;
    public ArticuloInsumoStockActual comprar(Long aLong, ArticuloInsumoStockActualDTO dto) throws Exception {
        try {
            Optional<ArticuloInsumoStockActual> articuloInsumoStockActual = articuloInsumoStockActualRepository.findById(aLong);
            articuloInsumoStockActual.get().setStockActual(dto.getStockActual());
            articuloInsumoStockActual.get().setFecha(dto.getFecha());
            

            return articuloInsumoStockActualRepository.save(articuloInsumoStockActual.get());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
