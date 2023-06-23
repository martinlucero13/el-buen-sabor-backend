package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.RubroDTO;
import com.utn.elbuensaborbackend.entities.Rubro;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RubroMapper extends BaseMapper<Rubro, RubroDTO> {

    static RubroMapper getInstance() {
        return Mappers.getMapper(RubroMapper.class);
    }

    @Mapping(source = "source.rubroPadre.id", target = "rubroPadreId")
    RubroDTO toDTO(Rubro source);

    @Mapping(target = "rubroPadre", ignore = true)
    @Mapping(target = "subRubros", ignore = true)
    Rubro toEntity(RubroDTO source);

    @IterableMapping(qualifiedByName = "toDTO")
    List<RubroDTO> toDTOsList(List<Rubro> source);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Rubro> toEntitiesList(List<RubroDTO> source);

    @Named("toDTO")
    default RubroDTO toDTO(RubroDTO source) {
        return source;
    }

    @Named("toEntity")
    default Rubro toEntity(Rubro source) {
        return source;
    }
}
