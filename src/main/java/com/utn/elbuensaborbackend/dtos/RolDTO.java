package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

@Data
public class RolDTO extends BaseDTO {

    private String auth0RolId;
    private String denominacion;
}
