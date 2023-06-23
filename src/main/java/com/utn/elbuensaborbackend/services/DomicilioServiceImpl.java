package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.DomicilioDTO;
import com.utn.elbuensaborbackend.dtos.LocalidadDTO;
import com.utn.elbuensaborbackend.entities.Domicilio;
import com.utn.elbuensaborbackend.entities.Localidad;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.DomicilioMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.DomicilioRepository;
import com.utn.elbuensaborbackend.services.interfaces.DomicilioService;
import com.utn.elbuensaborbackend.services.interfaces.LocalidadService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Transactional
    public Domicilio saveDomicilio(DomicilioDTO dto) throws Exception {
        try {
            Domicilio domicilio = baseMapper.toEntity(dto);

            LocalidadDTO localidadDTO = dto.getLocalidad();
            if (localidadDTO != null && localidadDTO.getId() == null) {
                Localidad localidad = localidadService.save(localidadDTO);
                domicilio.setLocalidad(localidad);
            }

            return domicilioRepository.save(domicilio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Domicilio updateDomicilio(Long id, DomicilioDTO dto) throws Exception {
        try {
            Optional<Domicilio> optional = domicilioRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Domicilio a actualizar no existe.");
            }

            Domicilio domicilio = optional.get();

            LocalidadDTO localidadDTO = dto.getLocalidad();
            if (localidadDTO.getId() == null) {
                Localidad localidad = localidadService.save(localidadDTO);
                domicilio.setLocalidad(localidad);
            } else {
                Localidad localidad = localidadService.update(localidadDTO.getId(), localidadDTO);
                domicilio.setLocalidad(localidad);
            }

            return domicilioRepository.save(domicilio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}