package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imagen")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_imagen"))
public class Imagen extends Base {

    @Column(name = "nombre")
	private String nombre;

    @ManyToOne
    @JoinColumn(name = "articulo_manufacturado_id")
	private ArticuloManufacturado articuloManufacturado;
}
