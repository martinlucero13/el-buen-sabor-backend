package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import com.utn.elbuensaborbackend.entities.Cliente;
import com.utn.elbuensaborbackend.entities.DetallePedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido, Long> {

    @Query(value = "SELECT detalle_pedido.* FROM detalle_pedido " +
            "WHERE pedido_id = :pedidoId", nativeQuery = true)
    List<DetallePedido> findByPedidoId(@Param("pedidoId") Long pedidoId);

    @Query("SELECT dp FROM DetallePedido dp " +
            "WHERE dp.pedido.fecha BETWEEN :fechaInicio AND :fechaFin " +
            "AND dp.pedido.estado = 'COMPLETADO'")
    List<DetallePedido> findDetailsBetweenDates(@Param("fechaInicio") Date fechaInicio,
                                                @Param("fechaFin") Date fechaFin);
    @Query("SELECT dp.articuloManufacturado FROM DetallePedido dp " +
            "WHERE dp.pedido.fecha BETWEEN :fechaInicio " +
            "AND :fechaFin AND dp.pedido.estado = 'COMPLETADO'" +
            "GROUP BY dp.articuloManufacturado ORDER BY SUM(dp.cantidad) DESC")
    List<ArticuloManufacturado> findTopProductsByOrderCount(@Param("fechaInicio") Date fechaInicio,
                                                            @Param("fechaFin") Date fechaFin);


    @Query("SELECT dp.pedido.cliente FROM DetallePedido dp " +
            "WHERE dp.pedido.fecha BETWEEN :fechaInicio " +
            "AND :fechaFin AND dp.pedido.estado = 'COMPLETADO'" +
            "GROUP BY dp.pedido.cliente " +
            "ORDER BY COUNT(dp.pedido) DESC")
    List<Cliente> findTopCustomersByOrderCount(Date fechaInicio, Date fechaFin);
}
