package com.utn.elbuensaborbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "articulo_insumo_precio_compra")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_articulo_insumo_precio_compra"))
public class ArticuloInsumoPrecioCompra extends Base {

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "monto")
    private Double monto;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "articulo_insumo_id")
    private ArticuloInsumo articuloInsumo;
}
