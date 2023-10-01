package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado, Long> {

    @Query("SELECT am FROM ArticuloManufacturado am " +
            "JOIN am.rubro r " +
            "WHERE (am.denominacion LIKE %:termino% " +
            "OR r.denominacion LIKE %:termino%) " +
            "AND am.bloqueado = FALSE")
    List<ArticuloManufacturado> findByTermino(@Param("termino") String termino);

    List<ArticuloManufacturado> findByBloqueadoFalse();

    List<ArticuloManufacturado> findAllByRubro_Id(Long rubroId);
}