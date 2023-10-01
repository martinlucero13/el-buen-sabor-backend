package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.pedido.DetallePedidoDTO;
import com.utn.elbuensaborbackend.dtos.pedido.MovimientosMonetariosDTO;
import com.utn.elbuensaborbackend.entities.*;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.DetallePedidoMapper;
import com.utn.elbuensaborbackend.repositories.*;
import com.utn.elbuensaborbackend.services.interfaces.DetallePedidoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetallePedidoServiceImpl
        extends BaseServiceImpl<DetallePedido, DetallePedidoDTO, Long> implements DetallePedidoService {
    @Autowired
    private ArticuloInsumoPrecioCompraRepository precioCompraRepository;

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    private DetallePedidoMapper detallePedidoMapper = DetallePedidoMapper.getInstance();

    @Autowired
    private PedidoRepository pedidoService;
    public DetallePedidoServiceImpl(BaseRepository<DetallePedido, Long> baseRepository,
                                    BaseMapper<DetallePedido, DetallePedidoDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Override
    public List<DetallePedidoDTO> findByPedidoId(Long pedidoId) throws Exception {
        try {
            List<DetallePedido> detallePedidos = detallePedidoRepository.findByPedidoId(pedidoId);
            return detallePedidos.stream()
                    .map(detallePedidoMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void saveCompraArticulos(List<DetallePedidoDTO> productos) throws Exception {
        try {
            for (DetallePedidoDTO producto : productos) {
                Long articuloManufacturadoId = producto.getArticuloManufacturado();
                Integer cantidad = producto.getCantidad();

                Optional<ArticuloManufacturado> articuloManufacturado = articuloManufacturadoRepository.findById(articuloManufacturadoId);

                for (DetalleArticuloManufacturado detalle : articuloManufacturado.get().getDetalles()) {
                    ArticuloInsumo insumo = detalle.getArticuloInsumo();
                    float cantidadRequerida = (float) (detalle.getCantidad() * cantidad);

                    if (insumo.getStockActual() < cantidadRequerida) {
                        throw new Exception("Stock insuficiente para comprar el artículo manufacturado.");
                    }

                    if (insumo.getBloqueado()) {
                        throw new Exception("No se puede comprar el artículo manufacturado porque un insumo está bloqueado.");
                    }

                    insumo.setStockActual(insumo.getStockActual() - cantidadRequerida);
                    articuloInsumoRepository.save(insumo);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al comprar los artículos manufacturados: " + e.getMessage());
        }
    }

    public List<DetallePedidoDTO> saveItems(List<DetallePedidoDTO> items) throws Exception {
        try {
            List<DetallePedidoDTO> savedItems = new ArrayList<>();

            for (DetallePedidoDTO itemDTO : items) {
                DetallePedido detallePedido = detallePedidoMapper.toEntity(itemDTO);
                ArticuloManufacturado articulo =
                        articuloManufacturadoRepository.findById(itemDTO.getArticuloManufacturado()).orElse(null);

                Pedido pedido = pedidoRepository.findByNumero(itemDTO.getPedido());
                detallePedido.setArticuloManufacturado(articulo);
                detallePedido.setPedido(pedido);

                DetallePedido savedItem = detallePedidoRepository.save(detallePedido);

                savedItems.add(detallePedidoMapper.toDTO(savedItem));
            }
            return savedItems;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ArticuloManufacturado> findArticulosMasPedidos(Date fechaInicio, Date fechaFin) throws Exception{
        try {
            return detallePedidoRepository.findTopProductsByOrderCount(fechaInicio, fechaFin);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Cliente> findClientesMasPedidos(Date fechaInicio, Date fechaFin) throws Exception {
        try {
            return detallePedidoRepository.findTopCustomersByOrderCount(fechaInicio, fechaFin);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public MovimientosMonetariosDTO findInformeMonetarios(Date fechaInicio, Date fechaFin) throws Exception{
        try {
            List<DetallePedido> detalles = detallePedidoRepository.findDetailsBetweenDates(fechaInicio, fechaFin);

            double ingresosTotales = 0;
            double costosTotales = 0;

            for (DetallePedido detalle : detalles) {
                Pedido pedidoDTO = pedidoService.findByNumero(detalle.getPedido().getNumeroPedido());

                ingresosTotales += pedidoDTO.getTotal();

                for (DetalleArticuloManufacturado detalleArticulo : detalle.getArticuloManufacturado().getDetalles()) {
                    double cantidad = detalleArticulo.getCantidad();
                    ArticuloInsumo insumo = detalleArticulo.getArticuloInsumo();

                    double precioInsumo = obtenerPrecioInsumo(insumo.getId());

                    costosTotales += precioInsumo * cantidad;
                }
            }

            double gananciasTotales = ingresosTotales - costosTotales;

            return new MovimientosMonetariosDTO(ingresosTotales, costosTotales, gananciasTotales);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    private double obtenerPrecioInsumo(Long idInsumo) throws Exception{
        try {
            Double precioCompra = precioCompraRepository.findLastByInsumoId(idInsumo);
            if (precioCompra != null) {
                return precioCompra;
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
