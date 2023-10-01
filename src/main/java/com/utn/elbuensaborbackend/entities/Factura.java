package com.utn.elbuensaborbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "factura")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_factura"))
public class Factura extends Base{

    @Column(name = "fecha_facturacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFacturacion;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
