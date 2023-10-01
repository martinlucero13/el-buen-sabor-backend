package com.utn.elbuensaborbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_pedido")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_detalle_pedido"))
public class DetallePedido extends Base {

    private Integer cantidad;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "articulo_manufacturado_id")
    private ArticuloManufacturado articuloManufacturado;
}
