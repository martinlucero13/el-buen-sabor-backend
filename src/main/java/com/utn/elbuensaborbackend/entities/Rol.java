package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rol")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_rol"))
public class Rol extends Base {

    @Column(name = "auth0_rol_id")
    private String auth0RolId;

    @Column(name = "denominacion", nullable = false, length = 20)
    private String denominacion;
}
