package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

@Data
public class UsuarioDTO extends BaseDTO {

    private String auth0Id;
    private String email;
    private Boolean bloqueado;
    private RolDTO rol;
}
