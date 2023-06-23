package com.utn.elbuensaborbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private Date fecha;
    private Date horaEstimadaFin;
    private Double montoDescuento;
    private ClienteDTO cliente;
    private TipoEntregaPedidoDTO tipoEntregaPedido;
    private TipoPagoPedidoDTO tipoPagoPedido;
}
