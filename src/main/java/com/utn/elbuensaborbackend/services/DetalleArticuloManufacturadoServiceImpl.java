package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.DetalleArticuloManufacturadoDTO;
import com.utn.elbuensaborbackend.entities.DetalleArticuloManufacturado;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.DetalleArticuloManufacturadoMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.DetalleArticuloManufacturadoRepository;
import com.utn.elbuensaborbackend.services.interfaces.DetalleArticuloManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleArticuloManufacturadoServiceImpl
        extends BaseServiceImpl<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDTO, Long>
        implements DetalleArticuloManufacturadoService {

    @Autowired
    private DetalleArticuloManufacturadoRepository detalleRepository;

    private final DetalleArticuloManufacturadoMapper detalleMapper = DetalleArticuloManufacturadoMapper.getInstance();

    public DetalleArticuloManufacturadoServiceImpl(
            BaseRepository<DetalleArticuloManufacturado, Long> baseRepository,
            BaseMapper<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }
}
