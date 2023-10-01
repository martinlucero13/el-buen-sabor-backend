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

	@Column(name = "forma_pago")
	private String formaPago;

	@Column(name = "metodo_pago")
	private String metodoPago;

	@Column(name = "nro_tarjeta")
	private String nroTarjeta;

	@Column(name = "estado")
	private String estado;
}
