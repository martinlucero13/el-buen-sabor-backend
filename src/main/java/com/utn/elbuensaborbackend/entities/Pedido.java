package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_pedido"))
public class Pedido extends Base {

    @Column(name = "fecha")
    @Temporal(TemporalType.TIME)
    private Date fecha;

    @Column(name = "hora_estimada_fin")
    @Temporal(TemporalType.TIME)
    private Date horaEstimadaFin;

    @Column(name = "monto_descuento")
    private Double montoDescuento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tipo_entrega_pedido_id")
    private TipoEntregaPedido tipoEntregaPedido;

    @ManyToOne
    @JoinColumn(name = "tipo_pago_pedido_id")
    private TipoPagoPedido tipoPagoPedido;
}
