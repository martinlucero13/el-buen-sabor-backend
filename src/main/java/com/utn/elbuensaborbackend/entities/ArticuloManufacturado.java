package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table(name = "articulo_manufacturado")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_articulo_manufacturado"))
public class ArticuloManufacturado extends Base {

    @Column(name = "denominacion", length = 20)
	private String denominacion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tiempo_estimado_cocina")
    @Temporal(TemporalType.TIME)
	private Time tiempoEstimadoCocina;

    @ManyToOne
    @JoinColumn(name = "rubro_id")
	private Rubro rubro;
}
