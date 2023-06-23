package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado, Long> {
    @Query(value = "SELECT am.* FROM articulo_manufacturado am " +
            "JOIN rubro r " +
            "ON am.rubro_id = r.id_rubro " +
            "WHERE am.denominacion LIKE %:termino% " +
            "OR r.denominacion " +
            "LIKE %:termino%", nativeQuery = true)
    List<ArticuloManufacturado> findByTermino(@Param("termino") String termino);
}