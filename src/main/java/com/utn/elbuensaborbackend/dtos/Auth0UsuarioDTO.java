package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class Auth0UsuarioDTO {

    private String email;

    private String clave;

    private List<RolDTO> roles;
}
