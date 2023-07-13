package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

@Data
public class UsuarioDTO extends BaseDTO {

    private String clave;

    private String usuario;

    private RolDTO rol;
}
