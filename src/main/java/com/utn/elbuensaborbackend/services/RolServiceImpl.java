package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.RolDTO;
import com.utn.elbuensaborbackend.entities.Rol;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.RolMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.RolRepository;
import com.utn.elbuensaborbackend.services.interfaces.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends BaseServiceImpl<Rol, RolDTO, Long> implements RolService {

    @Autowired
    private RolRepository rolRepository;

    private final RolMapper rolMapper = RolMapper.getInstance();

    public RolServiceImpl(BaseRepository<Rol, Long> baseRepository, BaseMapper<Rol, RolDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }
}
