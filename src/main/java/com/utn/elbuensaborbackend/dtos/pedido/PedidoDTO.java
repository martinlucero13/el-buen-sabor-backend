package com.utn.elbuensaborbackend.dtos.pedido;

import com.utn.elbuensaborbackend.dtos.BaseDTO;
import com.utn.elbuensaborbackend.dtos.factura.FacturaDTO;
import com.utn.elbuensaborbackend.enums.EstadoPedido;
import com.utn.elbuensaborbackend.enums.FormaPago;
import com.utn.elbuensaborbackend.enums.TipoEnvio;
import lombok.Data;

import java.util.Date;

@Data
public class PedidoDTO extends BaseDTO {

    private Date fecha;
    private EstadoPedido estado;
    private FormaPago formaPago;
    private TipoEnvio formaEntrega;
    private Double subtotal;
    private Double descuento;
    private Double total;
    private String numeroPedido;
    private Long cliente;
    private FacturaDTO factura;
    private String tiempoEstimadoPedido;
}
