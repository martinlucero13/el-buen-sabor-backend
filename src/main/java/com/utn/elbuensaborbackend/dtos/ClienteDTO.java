package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

@Data
public class ClienteDTO extends BaseDTO {
    
    private String nombre;

    private String apellido;

    private Long telefono;

    private UsuarioDTO usuario;

    private DomicilioDTO domicilio;
}
