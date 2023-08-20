package com.utn.elbuensaborbackend.services.interfaces;



import com.utn.elbuensaborbackend.dtos.DetallePedidoDTO;
import com.utn.elbuensaborbackend.dtos.pedido.MovimientosMonetariosDTO;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import com.utn.elbuensaborbackend.entities.Cliente;

import java.util.Date;
import java.util.List;

public interface DetallePedidoService{
    List<DetallePedidoDTO> findByPedidoId(Long id) throws Exception;

    List<ArticuloManufacturado> findArticulosMasPedidos(Date fechaInicio, Date fechaFin) throws Exception;

    List<Cliente> findClientesMasPedidos(Date fechaInicio, Date fechaFin) throws Exception;

    MovimientosMonetariosDTO findInformeMonetarios(Date fechaInicio, Date fechaFin) throws Exception;

    void saveCompraArticulos(List<com.utn.elbuensaborbackend.dtos.pedido.DetallePedidoDTO> ids ) throws Exception;

    List<com.utn.elbuensaborbackend.dtos.pedido.DetallePedidoDTO> saveItems(List<com.utn.elbuensaborbackend.dtos.pedido.DetallePedidoDTO> items) throws Exception;
}
