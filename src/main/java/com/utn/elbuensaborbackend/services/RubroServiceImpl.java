package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.RubroDTO;
import com.utn.elbuensaborbackend.entities.ArticuloInsumo;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import com.utn.elbuensaborbackend.entities.Rubro;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.RubroMapper;
import com.utn.elbuensaborbackend.repositories.ArticuloInsumoRepository;
import com.utn.elbuensaborbackend.repositories.ArticuloManufacturadoRepository;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.RubroRepository;
import com.utn.elbuensaborbackend.services.interfaces.ArticuloInsumoService;
import com.utn.elbuensaborbackend.services.interfaces.ArticuloManufacturadoService;
import com.utn.elbuensaborbackend.services.interfaces.RubroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RubroServiceImpl extends BaseServiceImpl<Rubro, RubroDTO, Long> implements RubroService  {

    @Autowired
    private RubroRepository rubroRepository;

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Autowired
    private ArticuloInsumoService articuloInsumoService;

    @Autowired
    private ArticuloManufacturadoService articuloManufacturadoService;

    private final RubroMapper rubroMapper = RubroMapper.getInstance();

    public RubroServiceImpl(BaseRepository<Rubro, Long> baseRepository, BaseMapper<Rubro, RubroDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Override
    public List<RubroDTO> findAllActivos() throws Exception {
        try {
            List<Rubro> rubros = rubroRepository.findAllByBloqueadoFalse();
            return rubroMapper.toDTOsList(rubros);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<RubroDTO> findByTipo(Boolean bool) throws Exception {
        try {
            List<Rubro> rubros = rubroRepository.findByTipo(bool);
            return rubroMapper.toDTOsList(rubros);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean existsByDenominacion(String denominacion) throws Exception {
        try {
            Rubro rubro = rubroRepository.findByDenominacion(denominacion);
            return rubro != null ? true : false;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Rubro save(RubroDTO dto) throws Exception {
        try {
            Rubro rubro = rubroMapper.toEntity(dto);

            if (dto.getRubroPadreId() != null) {
                if (rubroRepository.existsById(dto.getRubroPadreId())) {
                    Rubro rubroPadre = rubroRepository.findById(dto.getRubroPadreId()).get();
                    rubro.setRubroPadre(rubroPadre);
                } else {
                    throw new Exception("El Rubro Padre no existe.");
                }
            }

            return rubroRepository.save(rubro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Rubro update(Long id, RubroDTO dto) throws Exception {
        try {
            Optional<Rubro> optional = rubroRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Rubro a actualizar no existe.");
            }

            Rubro rubro = optional.get();

            if (dto.getRubroPadreId() != null) {
                if (rubroRepository.existsById(dto.getRubroPadreId())) {
                    Rubro rubroPadre = rubroRepository.findById(dto.getRubroPadreId()).get();
                    rubro.setRubroPadre(rubroPadre);
                } else {
                    throw new Exception("El Rubro Padre no existe.");
                }
            } else {
                rubro.setRubroPadre(null);
            }

            rubro.setDenominacion(dto.getDenominacion());
            rubro.setBloqueado(dto.getBloqueado());
            rubro.setEsInsumo(dto.getEsInsumo());

            return rubroRepository.save(rubro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Rubro updateEstado(Long id) throws Exception {
        try {
            Optional<Rubro> optional = rubroRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Rubro a actualizar no existe.");
            }

            Rubro rubro = optional.get();
            recursiveUpdateEstado(rubro);

            return rubro;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void recursiveUpdateEstado(Rubro rubro) throws Exception {
        rubro.setBloqueado(!rubro.getBloqueado());

        // Estado SubRubros
        if (rubro.getSubRubros() != null && !rubro.getSubRubros().isEmpty()) {
            for (Rubro subRubro : rubro.getSubRubros()) {
                recursiveUpdateEstado(subRubro);
            }
        }

        // Estados Artículos Insumos/Manufacturados relacionados
        if (rubro.getEsInsumo()) {
            List<ArticuloInsumo> articulosInsumos = articuloInsumoRepository.findAllByRubro_Id(rubro.getId());
            for (ArticuloInsumo articuloInsumo : articulosInsumos) {
                if (!articuloInsumo.getBloqueado()) {
                    articuloInsumoService.updateEstado(articuloInsumo.getId());
                }
            }
        } else {
            List<ArticuloManufacturado> articulosManufacturados = articuloManufacturadoRepository.findAllByRubro_Id(rubro.getId());
            for (ArticuloManufacturado articuloManufacturado : articulosManufacturados) {
                if (!articuloManufacturado.getBloqueado()) {
                    articuloManufacturadoService.updateEstado(articuloManufacturado.getId());
                }
            }
        }

        rubroRepository.save(rubro);
    }
}
