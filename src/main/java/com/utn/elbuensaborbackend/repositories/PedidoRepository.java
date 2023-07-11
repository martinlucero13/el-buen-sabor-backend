package com.utn.elbuensaborbackend.repositories;


import com.utn.elbuensaborbackend.entities.Pedido;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {

    @Query(value = "SELECT pedido.* FROM pedido " +
            "WHERE pedido.id_pedido LIKE %:termino% "
            , nativeQuery = true)
    List<Pedido> findByTermino(@Param("termino") String termino);

    @Query(value = "SELECT pedido.* FROM pedido " +
            "WHERE pedido.estado =:estado "
            , nativeQuery = true)
    List<Pedido> findByEstado(@Param("estado") String estado);

    @Query(value = "SELECT pedido.* FROM pedido " +
            "WHERE pedido.cliente_id =:idCliente "
            , nativeQuery = true)
    List<Pedido> findByCliente(@Param("idCliente") Long idCliente);

}
