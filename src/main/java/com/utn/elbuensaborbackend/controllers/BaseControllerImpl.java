package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.BaseDTO;
import com.utn.elbuensaborbackend.entities.Base;
import com.utn.elbuensaborbackend.services.interfaces.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase abstracta que implementa la interfaz BaseController para proporcionar funciones comunes.
 *
 * @param <E> Tipo de Entidad que extiende de Base.
 * @param <D> Tipo de DTO que extiende de BaseDTO.
 */
public class BaseControllerImpl<E extends Base, D extends BaseDTO> implements BaseController<E, D, Long> {

    @Autowired
    protected BaseService<E, D, Long> service;

    /**
     * Obtiene todos los registros de la entidad Base.
     *
     * @return ResponseEntity con el resultado de la operación.
     */
    @Override
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    /**
     * Obtiene un registro de la entidad Base por su ID.
     *
     * @param id ID del registro a obtener
     * @return ResponseEntity con el resultado de la operación.
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    /**
     * Guarda un registro de la entidad.
     *
     * @param dto DTO de la entidad Base a guardar.
     * @return ResponseEntity con el resultado de la operación.
     */
    @Override
    @PostMapping("")
    public ResponseEntity<?> save(D dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    /**
     * Actualiza un registro de la entidad por su ID.
     *
     * @param id ID del registro a actualizar.
     * @param dto DTO de la entidad Base a actualizar.
     * @return ResponseEntity con el resultado de la operación.
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(Long id, D dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    /**
     * Elimina un registro de la entidad Base por su ID.
     *
     * @param id ID del registro a eliminar.
     * @return ResponseEntity con el resultado de la operación.
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
