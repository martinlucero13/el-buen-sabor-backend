package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.factura.FacturaDTO;
import com.utn.elbuensaborbackend.entities.Factura;
import com.utn.elbuensaborbackend.entities.Pedido;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.FacturaMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.FacturaRepository;
import com.utn.elbuensaborbackend.services.interfaces.FacturaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FacturaServiceImpl extends BaseServiceImpl<Factura, FacturaDTO, Long> implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    private final FacturaMapper facturaMapper = FacturaMapper.getInstance();

    public FacturaServiceImpl(BaseRepository<Factura, Long> baseRepository, BaseMapper<Factura, FacturaDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Override
    public FacturaDTO findFacturaByPedidoId(Long pedidoId) throws Exception{
        try {
             Factura factura = facturaRepository.findByPedidoId(pedidoId);
            return facturaMapper.toDTO(factura);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Factura saveFactura(Pedido pedido, FacturaDTO facturaAltaDto) throws Exception {
        try {
            Factura factura = new Factura();
            factura.setFechaFacturacion(pedido.getFecha());
            factura.setPedido(pedido);

            factura.setCliente(pedido.getCliente());
            facturaMapper.updateFacturaFromDto(facturaAltaDto, factura);

            return facturaRepository.save(factura);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Factura updateFecha(Long id) throws Exception {
        try {
            Factura factura = facturaRepository.findById(id)
                    .orElseThrow(() -> new Exception("La factura a actualizar no existe."));

            factura.setFechaBaja(new Date());

            return facturaRepository.save(factura);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

