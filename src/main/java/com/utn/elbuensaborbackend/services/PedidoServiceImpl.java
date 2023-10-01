package com.utn.elbuensaborbackend.services;


import com.utn.elbuensaborbackend.dtos.pedido.PedidoDTO;

import com.utn.elbuensaborbackend.entities.*;
import com.utn.elbuensaborbackend.enums.EstadoPedido;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.repositories.ArticuloInsumoRepository;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.PedidoRepository;
import com.utn.elbuensaborbackend.services.interfaces.PedidoService;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl
        extends BaseServiceImpl<Pedido, PedidoDTO, Long> implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository,
                             BaseMapper<Pedido, PedidoDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Override
    public List<PedidoDTO> findAllByEstado(EstadoPedido estado) throws Exception {
        try {
            if (estado != null && StringUtils.isNotBlank(estado.name())) {
                return baseMapper.toDTOsList(pedidoRepository.findAllByEstado(estado));
            }
            return findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<PedidoDTO> findAllByCliente(Long id) throws Exception {
        try {
            if (id != null) {
                return baseMapper.toDTOsList(pedidoRepository.findAllByClienteId(id));
            }
            return findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Pedido> findPedidosEnCocina() throws Exception {
        try {
            return pedidoRepository.findAllByEstado(EstadoPedido.PREPARACION);
        } catch (Exception e) {
            throw new Exception("Error al obtener los pedidos en cocina: " + e.getMessage());
        }
    }

    @Override
    public int findTiempoCocina() throws Exception {
        try {
            List<Pedido> pedidosEnCocina = findPedidosEnCocina();

            int totalMinutos = 0;

            for (Pedido pedido : pedidosEnCocina) {
                for (DetallePedido detalle : pedido.getDetallesPedidos()) {
                    ArticuloManufacturado articulo = detalle.getArticuloManufacturado();
                    totalMinutos += articulo.getTiempoEstimadoCocina().toLocalTime().toSecondOfDay() / 60 * detalle.getCantidad();
                }
            }

            return totalMinutos;
        } catch (Exception e) {
            throw new Exception("Error al calcular el tiempo estimado de cocina: " + e.getMessage());
        }
    }

    @Transactional
    public Pedido updateEstado(Long id, EstadoPedido nuevoEstado) throws Exception {
        try {
            Optional<Pedido> optional = pedidoRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Pedido a actualizar no existe.");
            }
            Pedido pedido = optional.get();

            pedido.setEstado(nuevoEstado);

            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Pedido updateTiempo(Long id, String tiempo) throws Exception {
        try {
            Optional<Pedido> optional = pedidoRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Pedido a actualizar no existe.");
            }
            Pedido pedido = optional.get();

            pedido.setTiempoEstimadoPedido(tiempo);

            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public void updateStock(Long pedidoId) throws Exception {
        try {
            Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);

            if (pedidoOptional.isEmpty()) {
                throw new Exception("Pedido no encontrado");
            }

            Pedido pedido = pedidoOptional.get();

            if (pedido.getEstado() != EstadoPedido.CANCELADO) {
                throw new Exception("El pedido no está en estado cancelado");
            }

            List<DetallePedido> detalles = pedido.getDetallesPedidos();
            for (DetallePedido detalle : detalles) {
                ArticuloManufacturado articulo = detalle.getArticuloManufacturado();
                Integer cantidad = detalle.getCantidad();

                for (DetalleArticuloManufacturado detalleArticulo : articulo.getDetalles()) {
                    ArticuloInsumo insumo = detalleArticulo.getArticuloInsumo();
                    float cantidadRequerida = (float) (detalleArticulo.getCantidad() * cantidad);
                    insumo.setStockActual(insumo.getStockActual() + cantidadRequerida);
                    articuloInsumoRepository.save(insumo);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al cancelar el pedido y restaurar el stock: " + e.getMessage());
        }
    }
}
