package com.utn.elbuensaborbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rubro")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_rubro"))
public class Rubro extends Base  {

    @Column(name = "denominacion", nullable = false, length = 20)
    private String denominacion;

    @ManyToOne
    @JoinColumn(name = "rubro_padre_id")
    @JsonBackReference
    private Rubro rubroPadre;

    @OneToMany(mappedBy = "rubroPadre")
    @JsonManagedReference
    private List<Rubro> subRubros;
}
