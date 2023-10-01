package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Rubro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RubroRepository extends BaseRepository<Rubro, Long> {

    List<Rubro> findAllByBloqueadoFalse();

    @Query("SELECT r FROM Rubro r WHERE r.esInsumo = :bool")
    List<Rubro> findByTipo(@Param("bool") Boolean bool);

    @Query("SELECT r FROM Rubro r WHERE r.denominacion = :denominacion")
    Rubro findByDenominacion(@Param("denominacion") String denominacion);
}
