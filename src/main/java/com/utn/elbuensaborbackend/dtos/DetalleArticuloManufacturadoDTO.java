package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

@Data
public class DetalleArticuloManufacturadoDTO extends BaseDTO {

    private Double cantidad;
    private ArticuloInsumoDTO articuloInsumo;
}
