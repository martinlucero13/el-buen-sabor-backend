package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Rubro;
import com.utn.elbuensaborbackend.entities.UnidadMedida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RubroRepository extends BaseRepository<Rubro, Long> {

    @Query(value = "SELECT * FROM rubro WHERE rubro_padre_id IS NULL", nativeQuery = true)
    List<Rubro> findAllParents();

    @Query(value = "SELECT rubro.* FROM rubro " +
            "INNER JOIN articulo_insumo ON rubro.id_rubro = articulo_insumo.rubro_id " +
            "WHERE articulo_insumo.id_articulo_insumo = :articuloInsumoId", nativeQuery = true)
    Rubro findByInsumoId(@Param("articuloInsumoId") Long articuloInsumoId);

    @Query(value = "SELECT rubro.* FROM rubro " +
            "INNER JOIN articulo_manufacturado ON rubro.id_rubro = articulo_manufacturado.rubro_id " +
            "WHERE articulo_manufacturado.id_articulo_manufacturado = :articulomanufacturadoId", nativeQuery = true)
    Rubro findByManufacturadoId(@Param("articulomanufacturadoId") Long articulomanufacturadoId);
}
