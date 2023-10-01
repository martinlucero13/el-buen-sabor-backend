package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Factura;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends BaseRepository<Factura, Long> {
    Factura findByPedidoId(Long pedidoId);

}
