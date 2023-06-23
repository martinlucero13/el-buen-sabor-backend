package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.UnidadMedidaDTO;
import com.utn.elbuensaborbackend.entities.UnidadMedida;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.RolMapper;
import com.utn.elbuensaborbackend.mappers.RubroMapper;
import com.utn.elbuensaborbackend.mappers.UnidadMedidaMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.UnidadMedidaRepository;
import com.utn.elbuensaborbackend.services.interfaces.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadMedidaServiceImpl extends BaseServiceImpl<UnidadMedida, UnidadMedidaDTO, Long> implements UnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    private final UnidadMedidaMapper unidadMedidaMapper = UnidadMedidaMapper.getInstance();

    public UnidadMedidaServiceImpl(BaseRepository<UnidadMedida, Long> baseRepository, BaseMapper<UnidadMedida, UnidadMedidaDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }
}
