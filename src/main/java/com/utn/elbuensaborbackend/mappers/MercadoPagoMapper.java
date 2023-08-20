package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.MercadoPagoDatosDTO;
import com.utn.elbuensaborbackend.entities.MercadoPagoDatos;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MercadoPagoMapper extends BaseMapper<MercadoPagoDatos, MercadoPagoDatosDTO> {

    static MercadoPagoMapper getInstance() {
        return Mappers.getMapper(MercadoPagoMapper.class);
    }
}
