package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.DetallePedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido, Long> {

    @Query(value = "SELECT detalle_pedido.* FROM detalle_pedido " +
            "WHERE pedido_id = :pedidoId", nativeQuery = true)
    List<DetallePedido> findByPedidoId(@Param("pedidoId") Long pedidoId);
}
