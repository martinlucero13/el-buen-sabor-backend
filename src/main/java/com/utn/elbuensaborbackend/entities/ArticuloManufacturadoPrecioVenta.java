package com.utn.elbuensaborbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "monto")
    private Double monto;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "articulo_manufacturado_id")
    private ArticuloManufacturado articuloManufacturado;
}
