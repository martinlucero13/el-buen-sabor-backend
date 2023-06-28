package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Domicilio;
import com.utn.elbuensaborbackend.entities.TipoPagoPedido;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends BaseRepository<Domicilio, Long> {
    Domicilio findByClienteId(@Param("clienteId") Long clineteId);
}
