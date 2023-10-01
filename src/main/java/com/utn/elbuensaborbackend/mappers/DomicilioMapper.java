package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.DomicilioDTO;
import com.utn.elbuensaborbackend.entities.Domicilio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DomicilioMapper extends BaseMapper<Domicilio, DomicilioDTO> {

    static DomicilioMapper getInstance() {
        return Mappers.getMapper(DomicilioMapper.class);
    }
}
