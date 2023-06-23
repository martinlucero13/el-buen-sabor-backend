package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.RecetaDTO;
import com.utn.elbuensaborbackend.entities.Receta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface RecetaMapper extends BaseMapper<Receta, RecetaDTO> {

    static RecetaMapper getInstance() {
        return Mappers.getMapper(RecetaMapper.class);
    }

    RecetaDTO toDTO(Receta source);
    Receta toEntity(RecetaDTO source);
    List<RecetaDTO> toDTOsList(List<Receta> source);
    List<Receta> toEntitiesList(List<RecetaDTO> source);
}
