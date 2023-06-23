package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.ArticuloManufacturadoInsumo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloManufacturadoInsumoRepository extends BaseRepository<ArticuloManufacturadoInsumo, Long>{

    @Query(value = "SELECT * FROM articulo_manufacturado_insumo " +
            "WHERE articulo_manufacturado_id = :articuloManufacturadoId", nativeQuery = true)
    List<ArticuloManufacturadoInsumo> findByArticuloManufacturadoId(@Param("articuloManufacturadoId") Long articuloManufacturadoId);
}
