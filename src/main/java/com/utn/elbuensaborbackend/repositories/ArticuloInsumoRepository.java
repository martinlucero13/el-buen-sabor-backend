package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.ArticuloInsumo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloInsumoRepository extends BaseRepository<ArticuloInsumo, Long> {

    @Query(value = "SELECT * FROM articulo_insumo WHERE es_insumo = FALSE", nativeQuery = true)
    List<ArticuloInsumo> findBebidas();
}
