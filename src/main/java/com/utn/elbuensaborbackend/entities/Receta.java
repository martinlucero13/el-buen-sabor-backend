package com.utn.elbuensaborbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receta")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_receta"))
public class Receta extends Base {

    @Column(name = "descripcion", columnDefinition = "TEXT")
	private String descripcion;

    @OneToOne
    @JoinColumn(name = "articulo_manufacturado_id")
    @JsonIgnore
    private ArticuloManufacturado articuloManufacturado;
}
