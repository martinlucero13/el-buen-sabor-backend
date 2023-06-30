package com.utn.elbuensaborbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DetallePedidoDTO {
    private Long id;
    private Integer cantidad;
    private Double subTotal;
    private ArticuloManufacturadoDTO articuloManufacturado;
    private ArticuloInsumoDTO articuloInsumo;
}
