package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.ArticuloInsumoPrecioCompra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloInsumoPrecioCompraRepository extends BaseRepository<ArticuloInsumoPrecioCompra, Long>{

    @Query(value = "SELECT * FROM articulo_insumo_precio_compra" +
            " WHERE articulo_insumo_id = :articuloInsumoId ORDER BY fecha DESC LIMIT 1", nativeQuery = true)
    ArticuloInsumoPrecioCompra findByInsumoId(@Param("articuloInsumoId") Long articuloInsumoId);
}
