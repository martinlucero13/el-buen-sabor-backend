package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.BaseDTO;
import com.utn.elbuensaborbackend.entities.Base;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.services.interfaces.BaseService;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Clase abstracta que implementa la interfaz BaseService para proporcionar funcionalidades básicas de CRUD.
 *
 * @param <E> Tipo de Entidad que extiende de Base.
 * @param <D> Tipo de DTO que extiende de BaseDTO.
 * @param <ID> Tipo de ID que implementa Serializable.
 */
public abstract class BaseServiceImpl<E extends Base, D extends BaseDTO, ID extends Serializable>
        implements BaseService<E, D, ID> {

    protected BaseRepository<E, ID> baseRepository;

    protected BaseMapper<E, D> baseMapper;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository, BaseMapper<E, D> baseMapper) {
        this.baseRepository = baseRepository;
        this.baseMapper = baseMapper;
    }

    /**
     * Obtiene una lista de todos los DTOs de la entidad Base.
     *
     * @return List de DTOs de la entidad Base.
     * @throws Exception si ocurre algún error durante la operación.
     */
    @Override
    public List<D> findAll() throws Exception {
        try {
            List<E> entites = baseRepository.findAll();
            return baseMapper.toDTOsList(entites);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Obtiene el DTO de la entidad Base correspondiente al ID proporcionado.
     *
     * @param id ID de la entidad Base.
     * @return DTO de la entidad Base correspondiente al ID proporcionado.
     * @throws Exception si ocurre algún error durante la operación.
     */
    @Override
    public D findById(ID id) throws Exception {
        try {
            E entity = baseRepository.findById(id).get();
            return baseMapper.toDTO(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Busca una entidad específica por su ID y devuelve una instancia Optional del objeto.
     *
     * @param id ID de la entidad a buscar.
     * @return Una instancia Optional que contiene la entidad correspondiente al ID proporcionado.
     * @throws Exception si ocurre algún error durante la operación.
     */
    @Override
    @Transactional
    public Optional<E> findOptionalById(ID id) throws Exception {
        try {
            E entity = baseRepository.findById(id).get();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Guarda la entidad Base correspondiente al DTO proporcionado.
     *
     * @param dto DTO de la entidad Base a guardar.
     * @return La entidad Base guardada.
     * @throws Exception si ocurre algún error durante la operación.
     */
    @Override
    @Transactional
    public E save(D dto) throws Exception {
        try {
            return baseRepository.save(baseMapper.toEntity(dto));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Actualiza la entidad Base correspondiente al ID proporcionado con los datos del DTO proporcionado.
     *
     * @param id ID de la entidad Base a actualizar.
     * @param dto DTO con los nuevos datos de la entidad Base.
     * @return La entidad Base actualizada.
     * @throws Exception si ocurre algún error durante la operación.
     */
    @Override
    @Transactional
    public E update(ID id, D dto) throws Exception {
        try {
            Optional<E> optional = baseRepository.findById(id);

            if (optional.isEmpty()) {
                throw  new Exception("No se encontró la entidad a actualizar.");
            }

            return baseRepository.save(baseMapper.toEntity(dto));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Elimina la entidad Base correspondiente al ID proporcionado.
     *
     * @param id ID de la entidad Base a eliminar.
     * @throws Exception si ocurre algún error durante la operación.
     */
    @Override
    @Transactional
    public void delete(ID id) throws Exception {
        try {
            if (!baseRepository.existsById(id)) {
                throw new Exception("No se encontró la entidad a eliminar.");
            }

            baseRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
