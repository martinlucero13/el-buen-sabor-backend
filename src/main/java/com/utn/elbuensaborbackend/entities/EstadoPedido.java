package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "estado_pedido")
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @AttributeOverride(name = "id", column = @Column(name = "id_estado_pedido"))
public class EstadoPedido extends Base{

    @Column(name = "denominacion", length = 20)
    private String denominacion;

    @Column(name = "tiempo")
    @Temporal(TemporalType.TIME)
    private Date tiempo;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
