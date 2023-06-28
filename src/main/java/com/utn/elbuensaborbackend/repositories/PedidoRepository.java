package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import com.utn.elbuensaborbackend.entities.Domicilio;
import com.utn.elbuensaborbackend.entities.Pedido;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {
    List<Pedido> findByTermino(@Param("termino") String termino);
}
