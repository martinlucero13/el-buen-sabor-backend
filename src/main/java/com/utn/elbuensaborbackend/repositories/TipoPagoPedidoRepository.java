package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.TipoPagoPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagoPedidoRepository extends BaseRepository<TipoPagoPedido, Long> {

    @Query(value = "SELECT tipo_pago_pedido.* FROM tipo_pago_pedido " +
            "INNER JOIN pedido ON tipo_pago_pedido.id_tipo_pago_pedido = pedido.tipo_pago_pedido_id " +
            "WHERE pedido.id_pedido = :pedidoId", nativeQuery = true)
    TipoPagoPedido findByPedidoId(@Param("pedidoId") Long pedidoId);
}