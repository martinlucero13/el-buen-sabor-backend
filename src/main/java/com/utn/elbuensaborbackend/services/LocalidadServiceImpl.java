package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.LocalidadDTO;
import com.utn.elbuensaborbackend.entities.Localidad;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.LocalidadMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.LocalidadRepository;
import com.utn.elbuensaborbackend.services.interfaces.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, LocalidadDTO, Long> implements LocalidadService {

    @Autowired
    private LocalidadRepository localidadRepository;

    private final LocalidadMapper localidadMapper = LocalidadMapper.getInstance();

    public LocalidadServiceImpl(BaseRepository<Localidad, Long> baseRepository, BaseMapper<Localidad, LocalidadDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }
}