package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.*;
import com.utn.elbuensaborbackend.entities.*;
import com.utn.elbuensaborbackend.repositories.*;

import com.utn.elbuensaborbackend.services.interfaces.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TipoEntregaPedidoRepository tipoEntregaPedidoRepository;

    @Autowired
    private TipoPagoPedidoRepository tipoPagoPedidoRepository;

    @Autowired
    private DomicilioRepository domicilioRepository;



    @Override
    public List<PedidoDTO> findAll() throws Exception {
        try {
            List<Pedido> pedidos = pedidoRepository.findAll();
            List<PedidoDTO> pedidoDTOs = new ArrayList<>();

            for (Pedido am : pedidos) {
                PedidoDTO pedidoDTO = mapPedidoToDTO(am);
                pedidoDTOs.add(pedidoDTO);
            }

            return pedidoDTOs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PedidoDTO findById(Long id) throws Exception {
        try {
            Pedido pedido = pedidoRepository.findById(id).get();
            PedidoDTO pedidoDTO = mapPedidoToDTO(pedido);

            return pedidoDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<PedidoDTO> findByTermino(String termino) throws Exception {
        try {
            List<Pedido> pedidos = pedidoRepository.findByTermino(termino);
            List<PedidoDTO> pedidoDTOs = new ArrayList<>();

            for (Pedido pedido : pedidos) {
                PedidoDTO pedidoDTO = mapPedidoToDTO(pedido);
                pedidoDTOs.add(pedidoDTO);
            }

            return pedidoDTOs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<PedidoDTO> findByCliente(Long idCliente) throws Exception {
        try {
            List<Pedido> pedidos = pedidoRepository.findByCliente(idCliente);
            List<PedidoDTO> pedidoDTOs = new ArrayList<>();

            for (Pedido pedido : pedidos) {
                PedidoDTO pedidoDTO = mapPedidoToDTO(pedido);
                pedidoDTOs.add(pedidoDTO);
            }

            return pedidoDTOs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<PedidoDTO> findByEstado(String estado) throws Exception {
        try {
            List<Pedido> pedidos = pedidoRepository.findByEstado(estado);
            List<PedidoDTO> pedidoDTOs = new ArrayList<>();

            for (Pedido pedido : pedidos) {
                PedidoDTO pedidoDTO = mapPedidoToDTO(pedido);
                pedidoDTOs.add(pedidoDTO);
            }

            return pedidoDTOs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private PedidoDTO mapPedidoToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setFecha(pedido.getFecha());
        pedidoDTO.setHoraEstimadaFin(pedido.getHoraEstimadaFin());
        pedidoDTO.setMontoDescuento(pedido.getMontoDescuento());
        pedidoDTO.setPagado(pedido.isPagado());
        pedidoDTO.setEstado(pedido.getEstado());

        TipoEntregaPedido tipoEntregaPedido = tipoEntregaPedidoRepository.findByPedidoId(pedido.getId());

        TipoEntregaPedidoDTO tipoEntregaPedidoDTO = new TipoEntregaPedidoDTO();
        tipoEntregaPedidoDTO.setId(tipoEntregaPedido.getId());
        tipoEntregaPedidoDTO.setDescripcion(tipoEntregaPedido.getDescripcion());

        TipoPagoPedido tipoPagoPedido = tipoPagoPedidoRepository.findByPedidoId(pedido.getId());

        TipoPagoPedidoDTO tipoPagoPedidoDTO = new TipoPagoPedidoDTO();
        tipoPagoPedidoDTO.setId(tipoPagoPedido.getId());
        tipoPagoPedidoDTO.setDescripcion(tipoPagoPedido.getDescripcion());

        pedidoDTO.setTipoEntregaPedido(tipoEntregaPedidoDTO);
        pedidoDTO.setTipoPagoPedido(tipoPagoPedidoDTO);
        /*
        Cliente cliente = clienteRepository.findByPedidoId(pedido.getId());
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellido(cliente.getApellido());

        pedidoDTO.setCliente(clienteDTO);*/

        return pedidoDTO;
    }

    @Override
    public Pedido save(PedidoDTO entity) throws Exception {
        try {
            Pedido pedido = new Pedido();
            pedido.setFecha(entity.getFecha());
            pedido.setHoraEstimadaFin(entity.getHoraEstimadaFin());
            pedido.setMontoDescuento(entity.getMontoDescuento());

            //CLIENTE
            ClienteDTO clienteDTO = entity.getCliente();
            Cliente cLiente = new Cliente();
            cLiente.setId(clienteDTO.getId());
            pedido.setCliente(cLiente);

            //TIPO ENTREGA PEDIDO
            TipoEntregaPedidoDTO tipoEntregaPedidoDTO = entity.getTipoEntregaPedido();
            TipoEntregaPedido tipoEntregaPedido = new TipoEntregaPedido();
            tipoEntregaPedido.setId(tipoEntregaPedidoDTO.getId());
            pedido.setTipoEntregaPedido(tipoEntregaPedido);

            //TIPO PAGO PEDIDO
            TipoPagoPedidoDTO tipoPagoPedidoDTO = entity.getTipoPagoPedido();
            TipoPagoPedido tipoPagoPedido = new TipoPagoPedido();
            tipoPagoPedido.setId(tipoPagoPedidoDTO.getId());
            pedido.setTipoPagoPedido(tipoPagoPedido);

            pedidoRepository.save(pedido);



            /*articuloManufacturadoPrecioVenta.setFecha(articuloManufacturadoPrecioVentaDTO.getFecha());
            articuloManufacturadoPrecioVenta.setPrecioVenta(articuloManufacturadoPrecioVentaDTO.getPrecioVenta());
            articuloManufacturadoPrecioVenta.setArticuloManufacturado(articuloManufacturado);
            articuloManufacturadoPrecioVentaRepository.save(articuloManufacturadoPrecioVenta);*/
            return pedido;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Pedido update(Long id, PedidoDTO entity) throws Exception {
        try {
            Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
            if (optionalPedido.isPresent()) {
                Pedido pedido = optionalPedido.get();
                pedido.setFecha(entity.getFecha());
                pedido.setHoraEstimadaFin(entity.getHoraEstimadaFin());
                pedido.setMontoDescuento(entity.getMontoDescuento());

                return pedidoRepository.save(pedido);
            }
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            pedidoRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
