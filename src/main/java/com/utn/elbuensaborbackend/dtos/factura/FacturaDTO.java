package com.utn.elbuensaborbackend.dtos.factura;

import com.utn.elbuensaborbackend.dtos.BaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class FacturaDTO extends BaseDTO {

    private Date fechaFacturacion;
    private Long pedido;
    private Date fechaBaja;
    private Long cliente;
}