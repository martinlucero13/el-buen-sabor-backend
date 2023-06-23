package com.utn.elbuensaborbackend.mappers;


import com.utn.elbuensaborbackend.dtos.UnidadMedidaDTO;
import com.utn.elbuensaborbackend.entities.UnidadMedida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadMedidaMapper extends BaseMapper<UnidadMedida, UnidadMedidaDTO>{

    static UnidadMedidaMapper getInstance() {
        return Mappers.getMapper(UnidadMedidaMapper.class);
    }

    UnidadMedidaDTO toDTO(UnidadMedida source);
    UnidadMedida toEntity(UnidadMedidaDTO source);
    List<UnidadMedidaDTO> toDTOsList(List<UnidadMedida> source);
    List<UnidadMedida> toEntitiesList(List<UnidadMedidaDTO> source);
}
