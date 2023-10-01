package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Receta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends BaseRepository<Receta, Long> {

    Receta findByArticuloManufacturado_Id(Long articulManufacturadoId);
}
