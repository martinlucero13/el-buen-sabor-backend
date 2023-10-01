package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.UsuarioDTO;
import com.utn.elbuensaborbackend.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDTO> {

    static UsuarioMapper getInstance() {
        return Mappers.getMapper(UsuarioMapper.class);
    }
}
