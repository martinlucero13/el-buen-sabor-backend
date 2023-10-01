package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

@Data
public class ClienteDTO extends BaseDTO {
    
    private String nombre;
    private String apellido;
    private Long telefono;
    private DomicilioDTO domicilio;
    private UsuarioDTO usuario;
}
