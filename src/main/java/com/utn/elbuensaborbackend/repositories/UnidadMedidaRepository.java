package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.UnidadMedida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaRepository extends BaseRepository<UnidadMedida, Long> {
    @Query(value = "SELECT unidad_medida.* FROM unidad_medida " +
            "INNER JOIN articulo_insumo ON unidad_medida.id_unidad_medida = articulo_insumo.unidad_medida_id " +
            "WHERE articulo_insumo.id_articulo_insumo = :articuloInsumoId", nativeQuery = true)
    UnidadMedida findByInsumoId(@Param("articuloInsumoId") Long articuloInsumoId);
}
