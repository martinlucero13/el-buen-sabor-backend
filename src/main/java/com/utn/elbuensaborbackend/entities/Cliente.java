package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cliente")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_cliente"))
public class Cliente extends Base {

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 30)
    private String apellido;

    @Column(name = "telefono", nullable = false)
    private Long telefono;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
}
