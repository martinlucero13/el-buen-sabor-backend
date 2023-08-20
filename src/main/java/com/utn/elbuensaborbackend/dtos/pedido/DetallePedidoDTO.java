package com.utn.elbuensaborbackend.dtos.pedido;

import com.utn.elbuensaborbackend.dtos.BaseDTO;
import lombok.Data;

@Data
public class DetallePedidoDTO extends BaseDTO {
    private Integer cantidad;
    private Long articuloManufacturado;
    private String pedido;
}
