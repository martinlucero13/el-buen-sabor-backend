package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.TipoPagoPedido;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagoPedidoRepository extends BaseRepository<TipoPagoPedido, Long> {
    TipoPagoPedido findByPedidoId(@Param("pedidoId") Long pedidoId);
}