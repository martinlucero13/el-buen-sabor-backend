package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.pedido.DetallePedidoDTO;
import com.utn.elbuensaborbackend.dtos.pedido.MovimientosMonetariosDTO;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import com.utn.elbuensaborbackend.entities.Cliente;
import com.utn.elbuensaborbackend.entities.DetallePedido;

import java.util.Date;
import java.util.List;

public interface DetallePedidoService extends BaseService<DetallePedido, DetallePedidoDTO, Long> {

    List<DetallePedidoDTO> findByPedidoId(Long id) throws Exception;

    List<ArticuloManufacturado> findArticulosMasPedidos(Date fechaInicio, Date fechaFin) throws Exception;

    List<Cliente> findClientesMasPedidos(Date fechaInicio, Date fechaFin) throws Exception;

    MovimientosMonetariosDTO findInformeMonetarios(Date fechaInicio, Date fechaFin) throws Exception;

    void saveCompraArticulos(List<DetallePedidoDTO> ids ) throws Exception;

    List<DetallePedidoDTO> saveItems(List<DetallePedidoDTO> items) throws Exception;
}
