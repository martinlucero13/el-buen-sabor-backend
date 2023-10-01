package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.factura.FacturaDTO;
import com.utn.elbuensaborbackend.entities.Factura;
import com.utn.elbuensaborbackend.entities.Pedido;

public interface FacturaService extends BaseService<Factura, FacturaDTO, Long> {

    Factura saveFactura(Pedido pedido, FacturaDTO factura) throws Exception;

    FacturaDTO findFacturaByPedidoId(Long pedidoId) throws Exception;

    Factura updateFecha(Long id) throws Exception;
}