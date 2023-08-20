package com.utn.elbuensaborbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_articulo_manufacturado")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_detalle_articulo_manufacturado"))
public class DetalleArticuloManufacturado extends Base {

    @Column(name = "cantidad")
    private Double cantidad;

    @ManyToOne
    @JoinColumn(name = "articulo_insumo_id")
    private ArticuloInsumo articuloInsumo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "articulo_manufacturado_id")
    private ArticuloManufacturado articuloManufacturado;
}
