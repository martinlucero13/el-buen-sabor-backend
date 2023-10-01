package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.pedido.PedidoDTO;
import com.utn.elbuensaborbackend.entities.Cliente;
import com.utn.elbuensaborbackend.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidoMapper extends BaseMapper<Pedido, PedidoDTO> {

    static PedidoMapper getInstance() {
        return Mappers.getMapper(PedidoMapper.class);
    }

    @Mapping(target = "cliente", source = "cliente.id")
    PedidoDTO toDTO(Pedido pedido);

    Pedido toEntity(PedidoDTO dto);
    default Cliente mapToCliente(Long clienteId) {
        if (clienteId != null) {
            Cliente cliente = new Cliente();
            cliente.setId(clienteId);
            return cliente;
        }
        return null;
    }
}
