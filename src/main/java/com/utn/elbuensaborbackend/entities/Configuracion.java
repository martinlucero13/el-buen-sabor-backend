package com.utn.elbuensaborbackend.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "configuracion")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_configuracion"))
public class Configuracion extends Base {

    @Column(name = "cantidad_cocineros")
    private Integer cantidadCocineros;

    @Column(name = "email_empresa")
    private String emailEmpresa;

    @Column(name = "token_mercado_pago")
    private String tokenMercadoPago;
}
