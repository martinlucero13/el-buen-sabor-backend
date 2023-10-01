package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

import java.sql.Time;
import java.util.List;

@Data
public class ArticuloManufacturadoFullDTO extends BaseDTO {

    private String denominacion;
    private String imagen;
    private Integer stock;
    private Double precioVenta;
    private List<DetalleArticuloManufacturadoDTO> detalles;
    private String descripcion;
    private Boolean bloqueado;
    private Time tiempoEstimadoCocina;
    private String receta;
    private RubroDTO rubro;
}
