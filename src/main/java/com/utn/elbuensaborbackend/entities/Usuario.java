package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_usuario"))
public class Usuario extends Base {

    @Column(name = "auth0_id")
    private String auth0Id;

    @Column(name = "email")
    private String email;

    @Column(name = "bloqueado")
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean bloqueado;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
