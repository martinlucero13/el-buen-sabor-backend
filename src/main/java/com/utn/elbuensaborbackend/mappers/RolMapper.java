package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.RolDTO;
import com.utn.elbuensaborbackend.entities.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper extends BaseMapper<Rol, RolDTO> {

    static RolMapper getInstance() {
        return Mappers.getMapper(RolMapper.class);
    }

    RolDTO toDTO(Rol source);
    Rol toEntity(RolDTO source);
    List<RolDTO> toDTOsList(List<Rol> source);
    List<Rol> toEntitiesList(List<RolDTO> source);
}
