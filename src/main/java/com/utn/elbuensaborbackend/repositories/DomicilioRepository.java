package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Domicilio;
import com.utn.elbuensaborbackend.entities.TipoPagoPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends BaseRepository<Domicilio, Long> {

    @Query(value = "SELECT domicilio.* FROM domicilio " +
            "INNER JOIN cliente ON domicilio.id_domicilio=cliente.domicilio_id " +
            "WHERE cliente.id_cliente= :clienteId", nativeQuery = true)
    Domicilio findByClienteId(@Param("clienteId") Long clienteId);
}
