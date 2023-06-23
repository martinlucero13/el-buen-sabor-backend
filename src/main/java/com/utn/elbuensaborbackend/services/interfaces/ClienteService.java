package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.ClienteDTO;
import com.utn.elbuensaborbackend.entities.Cliente;

import java.util.List;


public interface ClienteService extends BaseService<Cliente, ClienteDTO, Long> {
    List<ClienteDTO> findAllClientesByRoles(List<String> roles) throws Exception;
    List<ClienteDTO> findAllClientesByName(String nombre) throws Exception;
    List<ClienteDTO> findAllClientesByApellido(String apellido) throws Exception;
    List<ClienteDTO> findAllClientesByNameAndApellido(String nombre, String apellido) throws Exception;
}