package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "domicilio")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_domicilio"))
public class Domicilio extends Base {

    @Column(name = "calle", nullable = false)
    private String calle;

    @Column(name = "numero")
    private Integer numero;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;
}
