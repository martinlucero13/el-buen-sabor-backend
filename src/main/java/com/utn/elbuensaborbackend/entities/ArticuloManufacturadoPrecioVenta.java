package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "articulo_manufacturado_precio_venta")
@AttributeOverride(name = "id", column = @Column(name = "id_precio_venta"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ArticuloManufacturadoPrecioVenta extends Base {

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "fecha")
    @Temporal(value = TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "articulo_manufacturado_id")
    private ArticuloManufacturado articuloManufacturado;
}
