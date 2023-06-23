package com.utn.elbuensaborbackend.services;


import com.utn.elbuensaborbackend.dtos.*;
import com.utn.elbuensaborbackend.entities.*;
import com.utn.elbuensaborbackend.repositories.*;
import com.utn.elbuensaborbackend.services.interfaces.ArticuloManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticuloManufacturadoServiceImpl implements ArticuloManufacturadoService {

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private RubroRepository rubroRepository;

    /* @Autowired
    private RecetaRepository recetaRepository;*/

    @Autowired
    private ArticuloManufacturadoPrecioVentaRepository articuloManufacturadoPrecioVentaRepository;

    @Override
    public List<ArticuloManufacturadoDTO> findAll() throws Exception {
        try {
            List<ArticuloManufacturado> articuloManufacturados = articuloManufacturadoRepository.findAll();
            List<ArticuloManufacturadoDTO> articulosManufacturadoDTOs = new ArrayList<>();

            for (ArticuloManufacturado am : articuloManufacturados) {
                ArticuloManufacturadoDTO articuloManufacturadoDTO = new ArticuloManufacturadoDTO();
                articuloManufacturadoDTO.setId(am.getId());
                articuloManufacturadoDTO.setDenominacion(am.getDenominacion());
                articuloManufacturadoDTO.setDescripcion(am.getDescripcion());
                articuloManufacturadoDTO.setTiempoEstimadoCocina(am.getTiempoEstimadoCocina());

                Imagen imagen = imagenRepository.findByArticuloManufacturadoId(am.getId());
                ImagenDTO imagenDTO = new ImagenDTO();
                imagenDTO.setId(imagen.getId());
                imagenDTO.setNombre(imagen.getNombre());

                /*Receta receta = recetaRepository.findByArticuloManufacturadoId(am.getId());
                RecetaDTO recetaDTO = new RecetaDTO();
                recetaDTO.setId(receta.getId());
                recetaDTO.setDescripcion(receta.getDescripcion());*/


                ArticuloManufacturadoPrecioVenta articuloManufacturadoPrecioVenta =
                        articuloManufacturadoPrecioVentaRepository.findByArticuloManufacturadoId(am.getId());
                ArticuloManufacturadoPrecioVentaDTO articuloManufacturadoPrecioVentaDTO =
                        new ArticuloManufacturadoPrecioVentaDTO();

                articuloManufacturadoPrecioVentaDTO.setId(articuloManufacturadoPrecioVenta.getId());
                articuloManufacturadoPrecioVentaDTO.setFecha(articuloManufacturadoPrecioVenta.getFecha());
                articuloManufacturadoPrecioVentaDTO.setPrecioVenta(articuloManufacturadoPrecioVenta.getPrecioVenta());

                //Rubro

                Rubro rubro =
                        rubroRepository.findByManufacturadoId(am.getId());

                RubroDTO rubroDTO =
                        new RubroDTO();
                rubroDTO.setId(rubro.getId());
                rubroDTO.setDenominacion(rubro.getDenominacion());


                //articuloManufacturadoDTO.setReceta(recetaDTO);
                articuloManufacturadoDTO.setRubro(rubroDTO);
                articuloManufacturadoDTO.setArticuloManufacturadoPrecioVenta(articuloManufacturadoPrecioVentaDTO);
                articuloManufacturadoDTO.setImagen(imagenDTO);
                articulosManufacturadoDTOs.add(articuloManufacturadoDTO);
            }

            return articulosManufacturadoDTOs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ArticuloManufacturadoDTO findById(Long id) throws Exception {
        try {
            ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.findById(id).get();
            ArticuloManufacturadoDTO articuloManufacturadoDTO = new ArticuloManufacturadoDTO();

            articuloManufacturadoDTO.setId(articuloManufacturado.getId());
            articuloManufacturadoDTO.setDenominacion(articuloManufacturado.getDenominacion());
            articuloManufacturadoDTO.setDescripcion(articuloManufacturado.getDescripcion());
            articuloManufacturadoDTO.setTiempoEstimadoCocina(articuloManufacturado.getTiempoEstimadoCocina());

            /*Receta receta = recetaRepository.findByArticuloManufacturadoId(id);
            RecetaDTO recetaDTO = new RecetaDTO();
            recetaDTO.setId(receta.getId());
            recetaDTO.setDescripcion(receta.getDescripcion());*/

            Rubro rubro =
                    rubroRepository.findByManufacturadoId(articuloManufacturado.getId());

            RubroDTO rubroDTO =
                    new RubroDTO();
            rubroDTO.setId(rubro.getId());
            rubroDTO.setDenominacion(rubro.getDenominacion());

            articuloManufacturadoDTO.setRubro(rubroDTO);

            Imagen imagen = imagenRepository.findByArticuloManufacturadoId(articuloManufacturado.getId());
            ImagenDTO imagenDTO = new ImagenDTO();

            imagenDTO.setId(imagen.getId());
            imagenDTO.setNombre(imagen.getNombre());

            ArticuloManufacturadoPrecioVenta articuloManufacturadoPrecioVenta =
                    articuloManufacturadoPrecioVentaRepository.findByArticuloManufacturadoId(articuloManufacturado.getId());
            ArticuloManufacturadoPrecioVentaDTO articuloManufacturadoPrecioVentaDTO =
                    new ArticuloManufacturadoPrecioVentaDTO();

            articuloManufacturadoPrecioVentaDTO.setId(articuloManufacturadoPrecioVenta.getId());
            articuloManufacturadoPrecioVentaDTO.setFecha(articuloManufacturadoPrecioVenta.getFecha());
            articuloManufacturadoPrecioVentaDTO.setPrecioVenta(articuloManufacturadoPrecioVenta.getPrecioVenta());
            //articuloManufacturadoDTO.setReceta(recetaDTO);
            articuloManufacturadoDTO.setImagen(imagenDTO);
            articuloManufacturadoDTO.setArticuloManufacturadoPrecioVenta(articuloManufacturadoPrecioVentaDTO);

            return articuloManufacturadoDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ArticuloManufacturadoDTO> findByTermino(String termino) throws Exception {
        try {
            List<ArticuloManufacturado> articuloManufacturados = articuloManufacturadoRepository.findByTermino(termino);
            List<ArticuloManufacturadoDTO> articulosManufacturadoDTOs = new ArrayList<>();

            for (ArticuloManufacturado am : articuloManufacturados) {
                ArticuloManufacturadoDTO articuloManufacturadoDTO = new ArticuloManufacturadoDTO();
                articuloManufacturadoDTO.setId(am.getId());
                articuloManufacturadoDTO.setDenominacion(am.getDenominacion());
                articuloManufacturadoDTO.setDescripcion(am.getDescripcion());
                articuloManufacturadoDTO.setTiempoEstimadoCocina(am.getTiempoEstimadoCocina());

                /*Receta receta = recetaRepository.findByArticuloManufacturadoId(am.getId());
                RecetaDTO recetaDTO = new RecetaDTO();
                recetaDTO.setId(receta.getId());
                recetaDTO.setDescripcion(receta.getDescripcion());*/

                Imagen imagen = imagenRepository.findByArticuloManufacturadoId(am.getId());
                ImagenDTO imagenDTO = new ImagenDTO();

                imagenDTO.setId(imagen.getId());
                    imagenDTO.setNombre(imagen.getNombre());

                    ArticuloManufacturadoPrecioVenta articuloManufacturadoPrecioVenta =
                        articuloManufacturadoPrecioVentaRepository.findByArticuloManufacturadoId(am.getId());
                ArticuloManufacturadoPrecioVentaDTO articuloManufacturadoPrecioVentaDTO =
                        new ArticuloManufacturadoPrecioVentaDTO();

                articuloManufacturadoPrecioVentaDTO.setId(articuloManufacturadoPrecioVenta.getId());
                articuloManufacturadoPrecioVentaDTO.setFecha(articuloManufacturadoPrecioVenta.getFecha());
                articuloManufacturadoPrecioVentaDTO.setPrecioVenta(articuloManufacturadoPrecioVenta.getPrecioVenta());
                //articuloManufacturadoDTO.setReceta(recetaDTO);
                articuloManufacturadoDTO.setImagen(imagenDTO);
                articuloManufacturadoDTO.setArticuloManufacturadoPrecioVenta(articuloManufacturadoPrecioVentaDTO);

                articulosManufacturadoDTOs.add(articuloManufacturadoDTO);
            }

            return articulosManufacturadoDTOs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ArticuloManufacturado save(ArticuloManufacturadoDTO entity) throws Exception {
        try {
            ArticuloManufacturado articuloManufacturado = new ArticuloManufacturado();
            articuloManufacturado.setDenominacion(entity.getDenominacion());
            articuloManufacturado.setTiempoEstimadoCocina(entity.getTiempoEstimadoCocina());
            articuloManufacturado.setDescripcion(entity.getDescripcion());

            //RUBRO
            RubroDTO rubroDTO = entity.getRubro();
            Rubro rubro = new Rubro();
            rubro.setId(rubroDTO.getId());
            articuloManufacturado.setRubro(rubro);

            articuloManufacturadoRepository.save(articuloManufacturado);

            //IMAGEN
            ImagenDTO imagenDTO = entity.getImagen();
            Imagen imagen = new Imagen();
            imagen.setNombre(imagenDTO.getNombre());
            imagen.setArticuloManufacturado(articuloManufacturado);
            imagenRepository.save(imagen);


            //PRECIO
            ArticuloManufacturadoPrecioVentaDTO articuloManufacturadoPrecioVentaDTO = entity.getArticuloManufacturadoPrecioVenta();
            ArticuloManufacturadoPrecioVenta articuloManufacturadoPrecioVenta = new ArticuloManufacturadoPrecioVenta();


            articuloManufacturadoPrecioVenta.setFecha(articuloManufacturadoPrecioVentaDTO.getFecha());
            articuloManufacturadoPrecioVenta.setPrecioVenta(articuloManufacturadoPrecioVentaDTO.getPrecioVenta());
            articuloManufacturadoPrecioVenta.setArticuloManufacturado(articuloManufacturado);
            articuloManufacturadoPrecioVentaRepository.save(articuloManufacturadoPrecioVenta);
            return articuloManufacturado;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ArticuloManufacturado update(Long id, ArticuloManufacturadoDTO entity) throws Exception {
        try {
            Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoRepository.findById(id);
            if (optionalArticuloManufacturado.isPresent()) {
                ArticuloManufacturado articuloManufacturado = optionalArticuloManufacturado.get();
                articuloManufacturado.setDenominacion(entity.getDenominacion());
                articuloManufacturado.setDescripcion(entity.getDescripcion());
                articuloManufacturado.setTiempoEstimadoCocina(entity.getTiempoEstimadoCocina());

                //Rubro
                RubroDTO rubroDTO = entity.getRubro();
                Optional<Rubro> rubro = rubroRepository
                        .findById(rubroDTO.getId());

                rubro.get().setId(rubroDTO.getId());
                rubro.get().setDenominacion(rubroDTO.getDenominacion());

                articuloManufacturado.setRubro(rubro.get());


                //Precio Compra
                ArticuloManufacturadoPrecioVentaDTO precioCompraDTO = entity.getArticuloManufacturadoPrecioVenta();
                ArticuloManufacturadoPrecioVenta precioCompra = articuloManufacturadoPrecioVentaRepository.findByArticuloManufacturadoId(id);

                if (precioCompra != null) {
                    precioCompra.setId(precioCompraDTO.getId());
                    precioCompra.setFecha(precioCompraDTO.getFecha());
                    precioCompra.setPrecioVenta(precioCompraDTO.getPrecioVenta());
                } else {
                    precioCompra = new ArticuloManufacturadoPrecioVenta();
                    precioCompra.setFecha(precioCompraDTO.getFecha());
                    precioCompra.setPrecioVenta(precioCompraDTO.getPrecioVenta());
                    precioCompra.setArticuloManufacturado(articuloManufacturado);
                }
                articuloManufacturadoPrecioVentaRepository.save(precioCompra);

                //Imagen

                ImagenDTO imagenDTO = entity.getImagen();
                Imagen imagen = imagenRepository.findByArticuloManufacturadoId(id);

                if (imagen != null) {
                    imagen.setNombre(imagenDTO.getNombre());
                    imagen.setArticuloManufacturado(articuloManufacturado);
                } else {
                    imagen = new Imagen();
                    imagen.setNombre(imagenDTO.getNombre());
                    imagen.setArticuloManufacturado(articuloManufacturado);
                }
                imagenRepository.save(imagen);


                return articuloManufacturadoRepository.save(articuloManufacturado);
            }
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            articuloManufacturadoRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
