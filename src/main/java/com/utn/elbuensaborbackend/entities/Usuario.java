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

    @Column(name = "usuario", nullable = false, length = 100)
    private String usuario;

    @Column(name = "clave", nullable = false, length = 30)
    private String clave;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
