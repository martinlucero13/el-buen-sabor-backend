package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

@Data
public class DomicilioDTO extends BaseDTO {

    private String calle;
    private Integer numero;
    private LocalidadDTO localidad;
}
