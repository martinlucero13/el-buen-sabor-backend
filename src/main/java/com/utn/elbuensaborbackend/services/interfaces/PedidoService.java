package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.pedido.PedidoDTO;
import com.utn.elbuensaborbackend.entities.Pedido;
import com.utn.elbuensaborbackend.enums.EstadoPedido;

import java.util.List;

public interface PedidoService extends BaseService<Pedido, PedidoDTO, Long> {

    List<PedidoDTO> findAllByEstado(EstadoPedido estado) throws Exception;
    List<PedidoDTO> findAllByCliente(Long id) throws Exception;

    List<Pedido> findPedidosEnCocina() throws Exception;

    Pedido updateEstado(Long id, EstadoPedido estado) throws Exception;

    void updateStock(Long pedidoId) throws Exception;

    Pedido updateTiempo(Long id, String tiempo) throws Exception;

    int findTiempoCocina() throws Exception;
}
