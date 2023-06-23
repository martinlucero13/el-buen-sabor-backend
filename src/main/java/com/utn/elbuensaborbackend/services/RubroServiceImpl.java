package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.RubroDTO;
import com.utn.elbuensaborbackend.entities.Rubro;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.RubroMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.RubroRepository;
import com.utn.elbuensaborbackend.services.interfaces.RubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RubroServiceImpl extends BaseServiceImpl<Rubro, RubroDTO, Long> implements RubroService  {

    @Autowired
    private RubroRepository rubroRepository;

    private final RubroMapper rubroMapper = RubroMapper.getInstance();

    public RubroServiceImpl(BaseRepository<Rubro, Long> baseRepository, BaseMapper<Rubro, RubroDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Override
    public List<RubroDTO> findAllParents() throws Exception {
        try {
            List<Rubro> rubros = rubroRepository.findAllParents();
            List<RubroDTO> dtos = new ArrayList<>();

            for (Rubro r : rubros) {
                RubroDTO rubroDTO = new RubroDTO();
                rubroDTO.setId(r.getId());
                rubroDTO.setDenominacion(r.getDenominacion());

                if (r.getRubroPadre() != null) {
                    rubroDTO.setRubroPadreId(r.getId());
                }

                dtos.add(rubroDTO);
            }

            return dtos;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public Rubro saveRubro(RubroDTO dto) throws Exception {
        try {
            Rubro rubro = rubroMapper.toEntity(dto);

            if (dto.getRubroPadreId() != null) {
                Rubro rubroPadre = rubroRepository.findById(dto.getRubroPadreId()).get();
                rubro.setRubroPadre(rubroPadre);
            }

            return rubroRepository.save(rubro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Rubro updateRubro(Long id, RubroDTO dto) throws Exception {
        try {
            Optional<Rubro> optional = rubroRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Rubro a actualizar no existe.");
            }

            Rubro rubro = optional.get();

            if (dto.getRubroPadreId() != null) {
                Rubro rubroPadre = rubroRepository.findById(dto.getRubroPadreId()).get();
                rubro.setRubroPadre(rubroPadre);
            } else {
                rubro.setRubroPadre(null);
            }

            return rubroRepository.save(rubro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
