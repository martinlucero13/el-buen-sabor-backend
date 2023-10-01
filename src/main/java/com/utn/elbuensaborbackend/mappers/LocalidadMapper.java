package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.LocalidadDTO;
import com.utn.elbuensaborbackend.entities.Localidad;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocalidadMapper extends BaseMapper<Localidad, LocalidadDTO> {

    static LocalidadMapper getInstance() {
        return Mappers.getMapper(LocalidadMapper.class);
    }
}
