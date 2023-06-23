package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

@Data
public class RubroDTO extends BaseDTO {

    private String denominacion;

    private Long rubroPadreId;
}
