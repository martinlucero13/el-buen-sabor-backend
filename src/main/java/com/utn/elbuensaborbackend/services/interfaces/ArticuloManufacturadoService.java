package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoDTO;
import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoFullDTO;
import com.utn.elbuensaborbackend.dtos.pedido.ArticuloCantidadDTO;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado, ArticuloManufacturadoFullDTO, Long> {

    List<ArticuloManufacturadoDTO> findAllActivos() throws Exception;
    List<ArticuloManufacturadoDTO> findAllSimple() throws Exception;
    ArticuloManufacturadoDTO findSimpleById(Long id) throws Exception;
    List<ArticuloManufacturadoDTO> findByTermino(String termino) throws Exception;
    ArticuloManufacturado saveFull(ArticuloManufacturadoFullDTO dto, MultipartFile file) throws Exception;
    ArticuloManufacturado updateFull(Long id, ArticuloManufacturadoFullDTO dto, MultipartFile file) throws Exception;
    ArticuloManufacturado updateEstado(Long id) throws Exception;
    boolean findStockDisponible(List<ArticuloCantidadDTO> articulosCantidad) throws Exception;
    List<ArticuloManufacturado> findArticulosEnCocina() throws Exception;
}
