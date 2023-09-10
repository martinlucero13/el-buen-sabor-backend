package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.UsuarioDTO;
import com.utn.elbuensaborbackend.entities.Usuario;


public interface UsuarioService extends BaseService<Usuario, UsuarioDTO, Long> {
    Boolean existsByEmail(String email) throws Exception;
    Integer findCantidadByRol(Long rolId) throws Exception;
}