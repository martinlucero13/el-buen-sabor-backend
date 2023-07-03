package com.utn.elbuensaborbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ArticuloManufacturadoDTO extends BaseDTO{

    private String denominacion;

    private String descripcion;

    private Time tiempoEstimadoCocina;

    private ImagenDTO imagen;

    private ArticuloManufacturadoPrecioVentaDTO articuloManufacturadoPrecioVenta;

    private RubroDTO rubro;

    private RecetaDTO receta;
}
