package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.TipoEntregaPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEntregaPedidoRepository extends BaseRepository<TipoEntregaPedido, Long> {

    @Query(value = "SELECT tipo_entrega_pedido.* FROM tipo_entrega_pedido " +
            "INNER JOIN pedido ON tipo_entrega_pedido.id_tipo_entrega_pedido = pedido.tipo_entrega_pedido_id " +
            "WHERE pedido.id_pedido = :pedidoId", nativeQuery = true)
    TipoEntregaPedido findByPedidoId(@Param("pedidoId") Long pedidoId);
}
