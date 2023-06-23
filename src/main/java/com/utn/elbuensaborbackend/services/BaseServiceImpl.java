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

public abstract class BaseServiceImpl<E extends Base, D extends BaseDTO, ID extends Serializable>
        implements BaseService<E, D, ID> {

    protected BaseRepository<E, ID> baseRepository;

    protected BaseMapper<E, D> baseMapper;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository, BaseMapper<E, D> baseMapper) {
        this.baseRepository = baseRepository;
        this.baseMapper = baseMapper;
    }

    @Override
    public List<D> findAll() throws Exception {
        try {
            List<E> entites = baseRepository.findAll();
            return baseMapper.toDTOsList(entites);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public D findById(ID id) throws Exception {
        try {
            E entity = baseRepository.findById(id).get();
            return baseMapper.toDTO(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(D dto) throws Exception {
        try {
            return baseRepository.save(baseMapper.toEntity(dto));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

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
