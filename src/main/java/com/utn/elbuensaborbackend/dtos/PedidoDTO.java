package com.utn.elbuensaborbackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private Date fecha;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date horaEstimadaFin;
    private Double montoDescuento;
    private boolean pagado;
    private String estado;
    private ClienteDTO cliente;
    private TipoEntregaPedidoDTO tipoEntregaPedido;
    private TipoPagoPedidoDTO tipoPagoPedido;
}
