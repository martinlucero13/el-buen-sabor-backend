package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.List;

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

    @OneToOne(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL)
    private Imagen imagen;

    @OneToOne(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL)
    private Receta receta;

    @OneToMany(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL)
    private List<ArticuloManufacturadoPrecioVenta> preciosVentas;

    @OneToMany(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL)
    private List<DetalleArticuloManufacturado> detalles;

    @OneToMany(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL)
    private List<DetallePedido> detallesPedidos;
    @Column(name = "bloqueado")
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean bloqueado;
}
