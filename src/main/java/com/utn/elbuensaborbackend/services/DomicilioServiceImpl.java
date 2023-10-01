package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.DomicilioDTO;
import com.utn.elbuensaborbackend.entities.Domicilio;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.DomicilioMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.DomicilioRepository;
import com.utn.elbuensaborbackend.services.interfaces.DomicilioService;
import com.utn.elbuensaborbackend.services.interfaces.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioServiceImpl extends BaseServiceImpl<Domicilio, DomicilioDTO, Long> implements DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private LocalidadService localidadService;

    private final DomicilioMapper domicilioMapper = DomicilioMapper.getInstance();

    public DomicilioServiceImpl(BaseRepository<Domicilio, Long> baseRepository, BaseMapper<Domicilio, DomicilioDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

}