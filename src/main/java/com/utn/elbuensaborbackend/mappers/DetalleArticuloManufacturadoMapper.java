package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.DetalleArticuloManufacturadoDTO;
import com.utn.elbuensaborbackend.entities.DetalleArticuloManufacturado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DetalleArticuloManufacturadoMapper extends BaseMapper<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDTO> {

    static DetalleArticuloManufacturadoMapper getInstance() {
        return Mappers.getMapper(DetalleArticuloManufacturadoMapper.class);
    }

}
