package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

@Data
public class UsuarioDTO extends BaseDTO {

    private String usuario;

    private RolDTO rol;
}
