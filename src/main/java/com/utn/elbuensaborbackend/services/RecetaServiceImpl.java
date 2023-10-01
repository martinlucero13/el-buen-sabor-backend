package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.RecetaDTO;
import com.utn.elbuensaborbackend.entities.Receta;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.RecetaMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.RecetaRepository;
import com.utn.elbuensaborbackend.services.interfaces.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetaServiceImpl extends BaseServiceImpl<Receta, RecetaDTO, Long> implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    private final RecetaMapper recetaMapper = RecetaMapper.getInstance();

    public RecetaServiceImpl(BaseRepository<Receta, Long> baseRepository, BaseMapper<Receta, RecetaDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Override
    public RecetaDTO findByArticuloManufacturadoId(Long articuloManufacturadoId) throws Exception {
        try {
            Receta receta = recetaRepository.findByArticuloManufacturado_Id(articuloManufacturadoId);
            return recetaMapper.toDTO(receta);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
