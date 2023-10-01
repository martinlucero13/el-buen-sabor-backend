package com.utn.elbuensaborbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class Auth0UsuarioDTO {

    private String email;
    private String clave;
    private Boolean bloqueado;
    private List<Auth0RolDTO> roles;
}
