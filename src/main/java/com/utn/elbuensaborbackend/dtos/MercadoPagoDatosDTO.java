package com.utn.elbuensaborbackend.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MercadoPagoDatosDTO {
    private Long id;

    private Long identificadorPago;

    private Date fechaCreacion;

    private Date fechaAprobacion;

    private String formaPago;

    private String metodoPago;

    private String nroTarjeta;

    private String estado;
}
