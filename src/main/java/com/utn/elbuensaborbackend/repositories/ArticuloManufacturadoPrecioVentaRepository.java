package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.ArticuloManufacturadoPrecioVenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloManufacturadoPrecioVentaRepository extends BaseRepository<ArticuloManufacturadoPrecioVenta, Long>{

    @Query("SELECT apv.monto FROM ArticuloManufacturadoPrecioVenta apv " +
            "WHERE apv.articuloManufacturado.id = :articuloManufacturadoId " +
            "ORDER BY apv.fecha DESC LIMIT 1")
    Double findLastByManufacturado(@Param("articuloManufacturadoId") Long articuloManufacturadoId);
}
