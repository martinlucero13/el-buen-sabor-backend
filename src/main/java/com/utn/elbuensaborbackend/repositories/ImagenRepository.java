package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Imagen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends BaseRepository<Imagen, Long>{

    @Query(value = "SELECT * FROM imagen WHERE articulo_manufacturado_id = :articuloManufacturadoId LIMIT 1", nativeQuery = true)
    Imagen findByManufacturadoId(@Param("articuloManufacturadoId") Long articuloManufacturadoId);

    @Query(value = "SELECT nombre FROM imagen WHERE articulo_manufacturado_id = :articuloManufacturadoId LIMIT 1", nativeQuery = true)
    String findLastByManufacturadoId(@Param("articuloManufacturadoId") Long articuloManufacturadoId);
}
