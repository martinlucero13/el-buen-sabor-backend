package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.ArticuloInsumoDTO;
import com.utn.elbuensaborbackend.dtos.ArticuloInsumoFullDTO;
import com.utn.elbuensaborbackend.dtos.ArticuloInsumoUpdateDTO;
import com.utn.elbuensaborbackend.entities.ArticuloInsumo;
import com.utn.elbuensaborbackend.entities.ArticuloInsumoPrecioCompra;
import com.utn.elbuensaborbackend.entities.DetalleArticuloManufacturado;
import com.utn.elbuensaborbackend.mappers.*;
import com.utn.elbuensaborbackend.repositories.*;
import com.utn.elbuensaborbackend.services.interfaces.ArticuloInsumoService;
import com.utn.elbuensaborbackend.services.interfaces.ArticuloManufacturadoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticuloInsumoServiceImpl extends BaseServiceImpl<ArticuloInsumo, ArticuloInsumoFullDTO, Long>
        implements ArticuloInsumoService {

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    @Autowired
    private ArticuloInsumoPrecioCompraRepository precioCompraRepository;

    @Autowired
    private ArticuloManufacturadoService articuloManufacturadoService;

    private final RubroMapper rubroMapper = RubroMapper.getInstance();

    private final ArticuloInsumoMapper articuloInsumoMapper = ArticuloInsumoMapper.getInstance();

    public ArticuloInsumoServiceImpl(
            BaseRepository<ArticuloInsumo, Long> baseRepository,
            BaseMapper<ArticuloInsumo, ArticuloInsumoFullDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Override
    public List<ArticuloInsumoFullDTO> findAll() throws Exception {
        try {
            List<ArticuloInsumo> articulosInsumos = articuloInsumoRepository.findAll();
            List<ArticuloInsumoFullDTO> dtos = articuloInsumoMapper.toDTOsList(articulosInsumos);

            for (ArticuloInsumo articuloInsumo : articulosInsumos) {
                // Precio Compra
                Double precioCompra = precioCompraRepository.findLastByInsumoId(articuloInsumo.getId());
                dtos.get(articulosInsumos.indexOf(articuloInsumo)).setPrecioCompra(precioCompra);
            }

            return dtos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ArticuloInsumoFullDTO findById(Long id) throws Exception {
        try {
            ArticuloInsumo articuloInsumo = articuloInsumoRepository.findById(id).get();
            ArticuloInsumoFullDTO dto = articuloInsumoMapper.toDTO(articuloInsumo);

            // Precio Compra
            Double precioCompra = precioCompraRepository.findLastByInsumoId(id);
            dto.setPrecioCompra(precioCompra);

            return dto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ArticuloInsumoDTO> findAllActivos() throws Exception {
        try {
            List<ArticuloInsumo> articulosInsumos = articuloInsumoRepository.findAllByBloqueadoFalse();
            return articuloInsumoMapper.toSimpleDTOsList(articulosInsumos);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ArticuloInsumoDTO> findAllSimple() throws Exception {
        try {
            List<ArticuloInsumo> articulosInsumos = articuloInsumoRepository.findAll();
            List<ArticuloInsumoDTO> dtos = articuloInsumoMapper.toSimpleDTOsList(articulosInsumos);

            for (ArticuloInsumo articuloInsumo : articulosInsumos) {
                // Precio Compra
                Double precioCompra = precioCompraRepository.findLastByInsumoId(articuloInsumo.getId());
                dtos.get(articulosInsumos.indexOf(articuloInsumo)).setPrecioCompra(precioCompra);
            }

            return dtos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ArticuloInsumoDTO findSimpleById(Long id) throws Exception {
        try {
            ArticuloInsumo articuloInsumo = articuloInsumoRepository.findById(id).get();
            ArticuloInsumoDTO dto = articuloInsumoMapper.toSimpleDTO(articuloInsumo);

            Double precioCompra = precioCompraRepository.findLastByInsumoId(id);
            dto.setPrecioCompra(precioCompra);

            return dto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ArticuloInsumo save(ArticuloInsumoFullDTO dto) throws Exception {
        try{
            ArticuloInsumo articuloInsumo = articuloInsumoMapper.toEntity(dto);
            articuloInsumo = articuloInsumoRepository.save(articuloInsumo);

            return articuloInsumo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ArticuloInsumo update(Long id, ArticuloInsumoFullDTO dto) throws Exception {
        try {
            Optional<ArticuloInsumo> optional = articuloInsumoRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Articulo Insumo a actualizar no existe.");
            }

            ArticuloInsumo articuloInsumo = optional.get();
            articuloInsumo.setDenominacion(dto.getDenominacion());
            articuloInsumo.setBloqueado(dto.getBloqueado());
            articuloInsumo.setEsInsumo(dto.getEsInsumo());
            articuloInsumo.setStockActual(dto.getStockActual());
            articuloInsumo.setStockMinimo(dto.getStockMinimo());
            articuloInsumo.setUnidadMedida(dto.getUnidadMedida());
            articuloInsumo.setRubro(rubroMapper.toEntity(dto.getRubro()));

            // Precio Compra
            Double precioCompraDB = precioCompraRepository.findLastByInsumoId(id);
            if (!Objects.equals(precioCompraDB, dto.getPrecioCompra())) {
                ArticuloInsumoPrecioCompra precioCompra = new ArticuloInsumoPrecioCompra(
                        new Date(), dto.getPrecioCompra(), articuloInsumo
                );
                articuloInsumo.getPreciosCompras().add(precioCompra);
            }

            articuloInsumo = articuloInsumoRepository.save(articuloInsumo);

            return articuloInsumo;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ArticuloInsumo updateEstado(Long id) throws Exception {
        try {
            Optional<ArticuloInsumo> optional = articuloInsumoRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Articulo Insumo a actualizar no existe.");
            }

            ArticuloInsumo articuloInsumo = optional.get();

            // Estado Artículos Manufacturados relacionados
            if (!articuloInsumo.getBloqueado()) {
                List<DetalleArticuloManufacturado> detalles = articuloInsumo.getDetalles();
                for (DetalleArticuloManufacturado detalle : detalles) {
                    if (Objects.equals(id, detalle.getArticuloInsumo().getId())) {
                        if (!detalle.getArticuloManufacturado().getBloqueado()) {
                            articuloManufacturadoService.updateEstado(detalle.getArticuloManufacturado().getId());
                        }
                    }
                }
            }

            // Estado Artículo Insumo
            articuloInsumo.setBloqueado(!articuloInsumo.getBloqueado());

            return articuloInsumoRepository.save(articuloInsumo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ArticuloInsumo updateStock(Long id, ArticuloInsumoUpdateDTO dto) throws Exception {
        try {
            Optional<ArticuloInsumo> optional = articuloInsumoRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Articulo Insumo a actualizar no existe.");
            }

            ArticuloInsumo articuloInsumo = optional.get();

            // Stock
            Float stockActual = articuloInsumo.getStockActual() + dto.getStockGanado() - dto.getStockPerdido();
            articuloInsumo.setStockActual(stockActual);

            // Precio Compra
            if (dto.getPrecioCompra() != null) {
                Double precioCompraDB = precioCompraRepository.findLastByInsumoId(id);
                if (!Objects.equals(precioCompraDB, dto.getPrecioCompra())) {
                    ArticuloInsumoPrecioCompra precioCompra = new ArticuloInsumoPrecioCompra(
                            new Date(), dto.getPrecioCompra(), articuloInsumo
                    );
                    articuloInsumo.getPreciosCompras().add(precioCompra);
                }
            }

            return articuloInsumoRepository.save(articuloInsumo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
