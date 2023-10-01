package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.RolDTO;
import com.utn.elbuensaborbackend.entities.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RolMapper extends BaseMapper<Rol, RolDTO> {

    static RolMapper getInstance() {
        return Mappers.getMapper(RolMapper.class);
    }
}
