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
    @Mapping(source = "source.rubroPadre.denominacion", target = "rubroPadreDenominacion")
    RubroDTO toDTO(Rubro source);

    @Mapping(target = "rubroPadre", ignore = true)
    @Mapping(target = "subRubros", ignore = true)
    Rubro toEntity(RubroDTO source);
}
