package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.ClienteDTO;
import com.utn.elbuensaborbackend.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDTO> {

    static ClienteMapper getInstance() {
        return Mappers.getMapper(ClienteMapper.class);
    }
}
