package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.pedido.DetallePedidoDTO;
import com.utn.elbuensaborbackend.entities.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper extends BaseMapper<DetallePedido, DetallePedidoDTO> {

    static DetallePedidoMapper getInstance() {
        return Mappers.getMapper(DetallePedidoMapper.class);
    }

    @Mapping(target = "articuloManufacturado", source = "articuloManufacturado.id")
    @Mapping(target = "pedido", source = "pedido.id")
    DetallePedidoDTO toDTO(DetallePedido detallePedido);

    DetallePedido toEntity(DetallePedidoDTO dto);

    default ArticuloManufacturado mapToArticuloManufacturado(Long articuloManufacturadoId) {
        if (articuloManufacturadoId != null) {
            ArticuloManufacturado articulo = new ArticuloManufacturado();
            articulo.setId(articuloManufacturadoId);
            return articulo;
        }
        return null;
    }

    default Pedido mapToPedido(String numeroPedido) {
        if (numeroPedido != null) {
            Pedido pedido = new Pedido();
            pedido.setNumeroPedido(numeroPedido);
            return pedido;
        }
        return null;
    }
}
