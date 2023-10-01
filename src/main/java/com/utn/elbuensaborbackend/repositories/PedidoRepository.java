package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Pedido;
import com.utn.elbuensaborbackend.enums.EstadoPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {
    @Query("SELECT p FROM Pedido p WHERE p.estado = :estado")
    List<Pedido> findAllByEstado(@Param("estado") EstadoPedido estado);

    @Query("SELECT p FROM Pedido p WHERE p.numeroPedido = :numeroPedido")
    Pedido findByNumero(@Param("numeroPedido") String numeroPedido);

    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId")
    List<Pedido> findAllByClienteId(@Param("clienteId") Long clienteId);
}
