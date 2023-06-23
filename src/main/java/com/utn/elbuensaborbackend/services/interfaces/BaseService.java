package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.BaseDTO;
import com.utn.elbuensaborbackend.entities.Base;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends Base, D extends BaseDTO, ID extends Serializable> {
    List<D> findAll() throws Exception;
    D findById(ID id) throws Exception;
    E save(D dto) throws Exception;
    E update(ID id, D dto) throws Exception;
    void delete(ID id) throws Exception;
}
