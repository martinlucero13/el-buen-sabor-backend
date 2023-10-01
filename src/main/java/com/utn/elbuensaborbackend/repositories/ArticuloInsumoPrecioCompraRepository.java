package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.ArticuloInsumoPrecioCompra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticuloInsumoPrecioCompraRepository extends BaseRepository<ArticuloInsumoPrecioCompra, Long> {

    @Query("SELECT aipc.monto FROM ArticuloInsumoPrecioCompra aipc " +
            "WHERE aipc.articuloInsumo.id = :articuloInsumoId " +
            "ORDER BY aipc.fecha DESC LIMIT 1")
    Double findLastByInsumoId(@Param("articuloInsumoId") Long articuloInsumoId);
}
