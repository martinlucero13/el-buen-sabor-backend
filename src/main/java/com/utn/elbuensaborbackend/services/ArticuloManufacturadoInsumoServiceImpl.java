package com.utn.elbuensaborbackend.services;


import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoDTO;
import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoInsumoDTO;
import com.utn.elbuensaborbackend.dtos.RubroDTO;
import com.utn.elbuensaborbackend.entities.ArticuloInsumo;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturadoInsumo;
import com.utn.elbuensaborbackend.entities.Rubro;
import com.utn.elbuensaborbackend.repositories.ArticuloInsumoRepository;
import com.utn.elbuensaborbackend.repositories.ArticuloManufacturadoInsumoRepository;
import com.utn.elbuensaborbackend.repositories.ArticuloManufacturadoRepository;
import com.utn.elbuensaborbackend.services.interfaces.ArticuloManufacturadoInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ArticuloManufacturadoInsumoServiceImpl implements ArticuloManufacturadoInsumoService {

    @Autowired
    private ArticuloManufacturadoInsumoRepository articuloManufacturadoInsumoRepository;
    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;


    @Override
    public List<ArticuloManufacturadoInsumoDTO> findByArticuloManufacturadoId(Long articuloManufacturadoId) throws Exception {
        try {
            List<ArticuloManufacturadoInsumo> entities =
                    articuloManufacturadoInsumoRepository.findByArticuloManufacturadoId(articuloManufacturadoId);
            List<ArticuloManufacturadoInsumoDTO> dtos = new ArrayList<>();

            for (ArticuloManufacturadoInsumo ami : entities) {
                ArticuloManufacturadoInsumoDTO dto = new ArticuloManufacturadoInsumoDTO();

                dto.setId(ami.getId());
                dto.setCantidad(ami.getCantidad());
                dto.setArticuloManufacturadoId(ami.getArticuloManufacturado().getId());
                dto.setArticuloInsumoId(ami.getArticuloInsumo().getId());

                dtos.add(dto);
            }

            return dtos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ArticuloManufacturadoInsumoDTO> findAll() throws Exception {
        return null;
    }

    @Override
    public ArticuloManufacturadoInsumoDTO findById(Long aLong) throws Exception {
        Optional<ArticuloManufacturadoInsumo> articuloManufacturadoInsumo = articuloManufacturadoInsumoRepository.findById(aLong);
        ArticuloManufacturadoInsumoDTO articuloManufacturadoInsumoDTO = new ArticuloManufacturadoInsumoDTO();
        articuloManufacturadoInsumoDTO.setId(articuloManufacturadoInsumo.get().getId());
        articuloManufacturadoInsumoDTO.setArticuloInsumoId(articuloManufacturadoInsumo.get().getArticuloInsumo().getId());
        articuloManufacturadoInsumoDTO.setArticuloManufacturadoId(articuloManufacturadoInsumo.get().getArticuloInsumo().getId());
        articuloManufacturadoInsumoDTO.setCantidad(articuloManufacturadoInsumo.get().getCantidad());
        return articuloManufacturadoInsumoDTO;
    }

    @Override
    public ArticuloManufacturadoInsumo save(ArticuloManufacturadoInsumoDTO dto) throws Exception {
        ArticuloManufacturadoInsumo articuloManufacturadoInsumo = new ArticuloManufacturadoInsumo();


        Optional<ArticuloManufacturado> articuloManufacturado = articuloManufacturadoRepository.findById(dto.getArticuloManufacturadoId());
        articuloManufacturadoInsumo.setArticuloManufacturado(articuloManufacturado.get());

        Optional<ArticuloInsumo> articuloInsumo = articuloInsumoRepository.findById(dto.getArticuloInsumoId());
        articuloManufacturadoInsumo.setArticuloInsumo(articuloInsumo.get());

        articuloManufacturadoInsumo.setCantidad(dto.getCantidad());
        System.out.println(articuloManufacturadoInsumo.getCantidad());
        System.out.println(articuloManufacturadoInsumo.getArticuloInsumo().getDenominacion());
        System.out.println(articuloManufacturadoInsumo.getArticuloManufacturado().getDenominacion());

        articuloManufacturadoInsumoRepository.save(articuloManufacturadoInsumo);
        return articuloManufacturadoInsumo;
    }

    @Override
    public void update(Long aLong, ArticuloManufacturadoInsumoDTO dto) throws Exception {
        Optional<ArticuloManufacturadoInsumo> optionalArticuloManufacturadoInsumo = articuloManufacturadoInsumoRepository.findById(aLong);

        if (optionalArticuloManufacturadoInsumo.isPresent()) {
            optionalArticuloManufacturadoInsumo.get().setCantidad(dto.getCantidad());

            articuloManufacturadoInsumoRepository.save(optionalArticuloManufacturadoInsumo.get());
        }
        else{
            System.out.println("Error");
        }
    }

    @Override
    public void delete(Long aLong) throws Exception {
        try {
            articuloManufacturadoInsumoRepository.deleteById(aLong);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
