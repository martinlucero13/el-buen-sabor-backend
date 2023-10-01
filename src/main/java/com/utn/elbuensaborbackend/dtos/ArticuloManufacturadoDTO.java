package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

import java.sql.Time;
import java.util.List;

@Data
public class ArticuloManufacturadoDTO extends BaseDTO {

    private String denominacion;
    private String descripcion;
    private String categoria;
    private String imagen;
    private Integer stock;
    private Double precioVenta;
    private Time tiempoEstimadoCocina;
    private List<DetalleArticuloManufacturadoDTO> detalles;
}
