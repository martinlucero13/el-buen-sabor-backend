package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Domicilio;
import com.utn.elbuensaborbackend.entities.Localidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadRepository extends BaseRepository<Localidad, Long> {
    @Query(value = "SELECT localidad.* FROM localidad " +
            "INNER JOIN domicilio ON localidad.id_localidad=domicilio.localidad_id " +
            "WHERE domicilio.id_domicilio= :domicilioId", nativeQuery = true)
    Localidad findByDomicilioId(@Param("domicilioId") Long domicilioId);
}
