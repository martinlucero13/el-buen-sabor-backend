package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

@Data
public class RubroDTO extends BaseDTO {

    private String denominacion;

    private Boolean bloqueado;

    private Boolean esInsumo;

    private Long rubroPadreId;

    private String rubroPadreDenominacion;
}
