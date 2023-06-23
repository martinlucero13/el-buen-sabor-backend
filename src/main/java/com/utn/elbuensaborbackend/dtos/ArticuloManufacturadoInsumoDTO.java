package com.utn.elbuensaborbackend.dtos;

import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ArticuloManufacturadoInsumoDTO extends BaseDTO{

    private Integer cantidad;

    private Long articuloManufacturadoId;

    private Long articuloInsumoId;
}
