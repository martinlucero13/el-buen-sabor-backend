package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.ArticuloInsumo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloInsumoRepository extends BaseRepository<ArticuloInsumo, Long> {

    List<ArticuloInsumo> findAllByBloqueadoFalse();
    List<ArticuloInsumo> findAllByRubro_Id(Long rubroId);
}
