package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.ArticuloInsumoStockMinimo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloInsumoStockMinimoRepository extends BaseRepository<ArticuloInsumoStockMinimo, Long> {

    @Query(value = "SELECT * FROM articulo_insumo_stock_minimo " +
            "WHERE articulo_insumo_id = :articuloInsumoId", nativeQuery = true)
    ArticuloInsumoStockMinimo findByInsumoId(@Param("articuloInsumoId") Long articuloInsumoId);
}
