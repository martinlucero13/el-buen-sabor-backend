package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.ImagenDTO;
import com.utn.elbuensaborbackend.entities.Imagen;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImagenMapper extends BaseMapper<Imagen, ImagenDTO> {

    static ImagenMapper getInstance() {
        return Mappers.getMapper(ImagenMapper.class);
    }

}
