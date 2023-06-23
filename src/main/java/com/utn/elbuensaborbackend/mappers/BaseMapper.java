package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.BaseDTO;
import com.utn.elbuensaborbackend.entities.Base;

import java.util.List;

public interface BaseMapper<E extends Base, D extends BaseDTO> {
    D toDTO(E source);
    E toEntity(D source);
    List<D> toDTOsList(List<E> source);
    List<E> toEntitiesList(List<D> source);
}
