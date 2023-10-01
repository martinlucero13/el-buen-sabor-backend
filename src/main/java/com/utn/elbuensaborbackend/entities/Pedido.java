package com.utn.elbuensaborbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.utn.elbuensaborbackend.enums.EstadoPedido;
import com.utn.elbuensaborbackend.enums.FormaPago;
import com.utn.elbuensaborbackend.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_pedido"))
public class Pedido extends Base {

    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @Column(name = "forma_pago")
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @Column(name = "forma_entrega")
    @Enumerated(EnumType.STRING)
    private TipoEnvio formaEntrega;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "descuento")
    private Double descuento;

    @Column(name = "total")
    private Double total;

    @Column(name = "numero_pedido")
    private String numeroPedido;

    @Column(name = "tiempo_estimado_pedido")
    private String tiempoEstimadoPedido;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<DetallePedido> detallesPedidos = new ArrayList<>();
}
