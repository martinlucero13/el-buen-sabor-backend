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

import java.util.Optional;


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
            Receta receta = recetaRepository.findByArticuloManufacturadoId(articuloManufacturadoId);
            RecetaDTO recetaDTO = new RecetaDTO();
            if (receta != null) {
                System.out.println("b");;
                recetaDTO.setId(receta.getId());
                recetaDTO.setDescripcion(receta.getDescripcion());

                return recetaDTO;
            }
            else{
                System.out.println("a");;
                recetaDTO.setId(0L);
                recetaDTO.setDescripcion("");
                return recetaDTO;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    public Receta saveReceta(Receta dto) throws Exception {
        try {
            System.out.println(dto.getArticuloManufacturado().getDenominacion());
            Receta receta = recetaRepository.save(dto);

            return recetaRepository.save(receta);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Receta updateReceta(Long aLong, RecetaDTO dto) throws Exception {
        try {
            Optional<Receta> receta = recetaRepository.findById(aLong);
            receta.get().setDescripcion(dto.getDescripcion());
            System.out.println(receta.get().getDescripcion());
            System.out.println(receta.get().getId());
            System.out.println(receta.get().getArticuloManufacturado());

            return recetaRepository.save(receta.get());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}