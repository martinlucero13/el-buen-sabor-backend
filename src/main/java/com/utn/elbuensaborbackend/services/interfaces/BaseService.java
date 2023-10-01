package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.BaseDTO;
import com.utn.elbuensaborbackend.entities.Base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz base para servicios que proporcionan funcionalidades básicas de CRUD.
 *
 * @param <E> Tipo de Entidad que extiende de Base.
 * @param <D> Tipo de DTO que extiende de BaseDTO.
 * @param <ID> TIpo de ID que implementa Serializable.
 */
public interface BaseService<E extends Base, D extends BaseDTO, ID extends Serializable> {
    List<D> findAll() throws Exception;
    D findById(ID id) throws Exception;
    Optional<E> findOptionalById(ID id) throws Exception;
    E save(D dto) throws Exception;
    E update(ID id, D dto) throws Exception;
    void delete(ID id) throws Exception;
}
