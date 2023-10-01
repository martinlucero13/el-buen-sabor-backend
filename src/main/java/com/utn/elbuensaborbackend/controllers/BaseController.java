package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.BaseDTO;
import com.utn.elbuensaborbackend.entities.Base;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

/**
 * Interfaz base para los controlladores que manejan entidades que extienden de Base.
 *
 * @param <E> Tipo de Entidad que extiende de Base.
 * @param <D> Tipo de DTO que extiende de BaseDTO.
 * @param <ID> Tipo de ID que implementa Serializable.
 */
public interface BaseController<E extends Base, D extends BaseDTO, ID extends Serializable> {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(@PathVariable ID id);
    ResponseEntity<?> save(@RequestBody D dto);
    ResponseEntity<?> update(@PathVariable ID id, @RequestBody D dto);
    ResponseEntity<?> delete(@PathVariable ID id);
}
