package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.ClienteDTO;
import com.utn.elbuensaborbackend.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDTO> {

    static ClienteMapper getInstance() {
        return Mappers.getMapper(ClienteMapper.class);
    }

    ClienteDTO toDTO(Cliente source);
    Cliente toEntity(ClienteDTO source);
    List<ClienteDTO> toDTOsList(List<Cliente> source);
    List<Cliente> toEntitiesList(List<ClienteDTO> source);
}
