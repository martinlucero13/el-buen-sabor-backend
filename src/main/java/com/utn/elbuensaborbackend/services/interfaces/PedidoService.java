package com.utn.elbuensaborbackend.services.interfaces;


import com.utn.elbuensaborbackend.dtos.PedidoDTO;
import com.utn.elbuensaborbackend.entities.Pedido;

import java.util.Date;
import java.util.List;

public interface PedidoService {
    List<PedidoDTO> findAll() throws Exception;

    PedidoDTO findById(Long id) throws  Exception;

    List<PedidoDTO> findByTermino(String termino) throws Exception;

    List<PedidoDTO> findByCliente(Long idCliente) throws Exception;

    List<PedidoDTO> findByEstado(String estado) throws Exception;

    Pedido save(PedidoDTO entity) throws Exception;

    Pedido update(Long id, PedidoDTO entity) throws Exception;

    PedidoDTO updateFecha(Long id, PedidoDTO entity) throws Exception;

    PedidoDTO updateEstado(Long id, String estado) throws Exception;

    void delete(Long id) throws Exception;

}
