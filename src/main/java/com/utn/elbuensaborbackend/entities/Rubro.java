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
public class Rubro extends Base {

    @Column(name = "denominacion")
    private String denominacion;

    @Column(name = "bloqueado")
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean bloqueado;

    @Column(name = "es_insumo")
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean esInsumo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rubro_padre_id")
    @JsonBackReference
    private Rubro rubroPadre;

    @OneToMany(mappedBy = "rubroPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Rubro> subRubros;

    public void addSubRubro(Rubro subRubro) {
        subRubros.add(subRubro);
        subRubro.setRubroPadre(this);
    }
}
