package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.ClienteDTO;
import com.utn.elbuensaborbackend.entities.Cliente;

import java.util.List;

public interface ClienteService extends BaseService<Cliente, ClienteDTO, Long> {

    List<ClienteDTO> findAllEmpleados() throws Exception;
    List<ClienteDTO> findAllClientes() throws Exception;
    ClienteDTO findClienteByUsuarioAuth0Id(String auht0Id) throws Exception;
    ClienteDTO updateEstado(Long id) throws Exception;
    ClienteDTO findClienteByPedido(Long id) throws Exception;

}