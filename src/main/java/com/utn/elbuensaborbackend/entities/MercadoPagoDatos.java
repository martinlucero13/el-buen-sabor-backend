package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "mercado_pago_datos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_mercado_pago_datos"))
public class MercadoPagoDatos extends Base {

	@Column(name = "identificador_pago")
	private Long identificadorPago;

	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.TIME)
	private Date fechaCreacion;

	@Column(name = "fecha_aprobacion")
	@Temporal(TemporalType.TIME)
	private Date fechaAprobacion;

	@Column(name = "forma_pago", length=50)
	private String formaPago;

	@Column(name = "metodo_pago", length=50)
	private String metodoPago;

	@Column(name = "nro_tarjeta", length=50)
	private String nroTarjeta;

	@Column(name = "estado", length=20)
	private String estado;
}
