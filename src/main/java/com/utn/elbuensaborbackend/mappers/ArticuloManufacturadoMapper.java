package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoDTO;
import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoFullDTO;
import com.utn.elbuensaborbackend.entities.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticuloManufacturadoMapper extends BaseMapper<ArticuloManufacturado, ArticuloManufacturadoFullDTO> {

    static ArticuloManufacturadoMapper getInstance() {
        return Mappers.getMapper(ArticuloManufacturadoMapper.class);
    }

    final DetalleArticuloManufacturadoMapper detalleMapper = DetalleArticuloManufacturadoMapper.getInstance();

    @Override
    @Mapping(source = "source.imagen.nombre", target = "imagen")
    @Mapping(source = "source.receta.descripcion", target = "receta")
    ArticuloManufacturadoFullDTO toDTO(ArticuloManufacturado source);

    @Mapping(target = "source.rubro", ignore = true)
    @Mapping(target = "source.receta", ignore = true)
    @Mapping(source = "source.imagen.nombre", target = "imagen")
    @Mapping(source = "source.rubro.denominacion", target = "categoria")
    ArticuloManufacturadoDTO toSimpleDTO(ArticuloManufacturado source);

    List<ArticuloManufacturadoDTO> toSimpleDTOsList(List<ArticuloManufacturado> source);

    @Override
    @Mapping(source = "source.imagen", target = "imagen.nombre")
    @Mapping(source = "source.receta", target = "receta.descripcion")
    @Mapping(target = "source.tiempoEstimadoCocina", dateFormat = "HH:mm:ss")
    ArticuloManufacturado toEntity(ArticuloManufacturadoFullDTO source);

    @AfterMapping
    default void setArticuloManufacturado(ArticuloManufacturadoFullDTO source, @MappingTarget ArticuloManufacturado target) {
        if (source.getImagen() != null) {
            target.setImagen(new Imagen(source.getImagen(), target));
        }

        if (source.getReceta() != null) {
            target.setReceta(new Receta(source.getReceta(), target));
        }

        if (source.getPrecioVenta() != null) {
            if (target.getPreciosVentas() == null) {
                target.setPreciosVentas(new ArrayList<>());
            }
            target.getPreciosVentas().add(new ArticuloManufacturadoPrecioVenta(new Date(), source.getPrecioVenta(), target));
        }

        if (source.getDetalles() != null) {
            if (target.getDetalles() == null) {
                target.setDetalles(new ArrayList<>());
            }
            List<DetalleArticuloManufacturado> detalles = detalleMapper.toEntitiesList(source.getDetalles());
            detalles.stream().forEach(detalle -> detalle.setArticuloManufacturado(target));
            target.setDetalles(detalles);
        }
    }
}