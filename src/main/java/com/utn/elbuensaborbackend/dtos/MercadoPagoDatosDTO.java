package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class MercadoPagoDatosDTO extends BaseDTO {

    private Long identificadorPago;
    private Date fechaCreacion;
    private Date fechaAprobacion;
    private String formaPago;
    private String metodoPago;
    private String nroTarjeta;
    private String estado;
}
