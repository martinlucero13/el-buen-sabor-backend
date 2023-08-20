package com.utn.elbuensaborbackend.entities;

import com.utn.elbuensaborbackend.enums.FormaPago;
import com.utn.elbuensaborbackend.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_pedido"))
public class Pedido extends Base {

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "hora_estimada_fin")
    @Temporal(TemporalType.TIME)
    private Date horaEstimadaFin;

    @Column(name = "monto_descuento")
    private Double montoDescuento;

    @Column(name = "pagado")
    private boolean pagado;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tipo_entrega_pedido_id")
    private TipoEntregaPedido tipoEntregaPedido;

    @ManyToOne
    @JoinColumn(name = "tipo_pago_pedido_id")
    private TipoPagoPedido tipoPagoPedido;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<DetallePedido> detallesPedidos = new ArrayList<>();
}
