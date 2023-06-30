package com.utn.elbuensaborbackend.services.interfaces;



import com.utn.elbuensaborbackend.dtos.DetallePedidoDTO;

import java.util.List;

public interface DetallePedidoService{
    List<DetallePedidoDTO> findByPedidoId(Long id) throws Exception;
}
