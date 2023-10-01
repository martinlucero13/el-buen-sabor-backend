package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.factura.FacturaDTO;
import com.utn.elbuensaborbackend.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FacturaMapper extends BaseMapper<Factura, FacturaDTO> {

    static FacturaMapper getInstance() {
        return Mappers.getMapper(FacturaMapper.class);
    }

    @Mapping(target = "cliente", source = "cliente.id")
    @Mapping(target = "pedido", source = "pedido.id")
    FacturaDTO toDTO(Factura factura);

    Factura toEntity(FacturaDTO dto);

    default Cliente mapToCliente(Long clienteId) {
        if (clienteId != null) {
            Cliente cliente = new Cliente();
            cliente.setId(clienteId);
            return cliente;
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

    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    void updateFacturaFromDto(FacturaDTO facturaDto, @MappingTarget Factura factura);
}