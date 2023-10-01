package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.ArticuloInsumoDTO;
import com.utn.elbuensaborbackend.dtos.ArticuloInsumoFullDTO;
import com.utn.elbuensaborbackend.entities.ArticuloInsumo;
import com.utn.elbuensaborbackend.entities.ArticuloInsumoPrecioCompra;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoFullDTO> {

    static ArticuloInsumoMapper getInstance(){
        return Mappers.getMapper(ArticuloInsumoMapper.class);
    }

    @Mapping(target = "source.rubro", ignore = true)
    @Mapping(target = "source.esInsumo", ignore = true)
    @Mapping(target = "source.stockActual", ignore = true)
    @Mapping(target = "source.stockMinimo", ignore = true)
    ArticuloInsumoDTO toSimpleDTO(ArticuloInsumo source);

    List<ArticuloInsumoDTO> toSimpleDTOsList(List<ArticuloInsumo> source);

    @AfterMapping
    default void setArticuloInsumo(ArticuloInsumoFullDTO source, @MappingTarget ArticuloInsumo target) {
        if (source.getPrecioCompra() != null) {
            if (target.getPreciosCompras() == null) {
                target.setPreciosCompras(new ArrayList<>());
            }

            target.getPreciosCompras().add(new ArticuloInsumoPrecioCompra(new Date(), source.getPrecioCompra(), target));
        }
    }
}
