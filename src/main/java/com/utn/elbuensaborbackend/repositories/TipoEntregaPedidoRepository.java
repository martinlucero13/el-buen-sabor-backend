package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.TipoEntregaPedido;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEntregaPedidoRepository extends BaseRepository<TipoEntregaPedido, Long> {
    TipoEntregaPedido findByPedidoId(@Param("pedidoId") Long pedidoId);
}
