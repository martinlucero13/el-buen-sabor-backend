package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.RecetaDTO;
import com.utn.elbuensaborbackend.entities.Receta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecetaMapper extends BaseMapper<Receta, RecetaDTO> {

    static RecetaMapper getInstance() {
        return Mappers.getMapper(RecetaMapper.class);
    }

}
