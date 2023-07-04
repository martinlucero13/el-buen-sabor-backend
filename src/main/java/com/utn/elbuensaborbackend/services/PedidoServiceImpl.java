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
                PedidoDTO pedidoDTO = new PedidoDTO();
                pedidoDTO.setId(am.getId());
                pedidoDTO.setFecha(am.getFecha());
                pedidoDTO.setHoraEstimadaFin(am.getHoraEstimadaFin());
                pedidoDTO.setMontoDescuento(am.getMontoDescuento());




                //Cliente

                Cliente cliente = clienteRepository.findByPedidoId(am.getId());

                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setId(cliente.getId());
                clienteDTO.setNombre(cliente.getNombre());
                clienteDTO.setApellido(cliente.getApellido());
                //????clienteDTO.getDomicilio(cliente.getDomicilio());
                //Domicilio tabla asociada de cliente
                /*Domicilio domicilio =
                        domicilioRepository.findByClienteId(cliente.getId());

                DomicilioDTO domicilioDTO =
                        new DomicilioDTO();
                domicilioDTO.setId(domicilio.getId());
                domicilioDTO.setCalle(domicilio.getCalle());
                domicilioDTO.setNumero(domicilio.getNumero());

                clienteDTO.setDomicilio(domicilioDTO);
                clienteDTO.setTelefono(cliente.getTelefono());*/
                //????clienteDTO.setUsuario(cliente.getUsuario());

                //Tipo Entrega Pedido

                TipoEntregaPedido tipoEntregaPedido = tipoEntregaPedidoRepository.findByPedidoId(am.getId());

                TipoEntregaPedidoDTO tipoEntregaPedidoDTO = new TipoEntregaPedidoDTO();
                tipoEntregaPedidoDTO.setId(tipoEntregaPedido.getId());
                tipoEntregaPedidoDTO.setDescripcion(tipoEntregaPedido.getDescripcion());

                //Tipo Pago Pedido

                TipoPagoPedido tipoPagoPedido = tipoPagoPedidoRepository.findByPedidoId(am.getId());

                TipoPagoPedidoDTO tipoPagoPedidoDTO = new TipoPagoPedidoDTO();
                tipoPagoPedidoDTO.setId(tipoPagoPedido.getId());
                tipoPagoPedidoDTO.setDescripcion(tipoPagoPedido.getDescripcion());


                pedidoDTO.setCliente(clienteDTO);
                pedidoDTO.setTipoEntregaPedido(tipoEntregaPedidoDTO);
                pedidoDTO.setTipoPagoPedido(tipoPagoPedidoDTO);
                pedidoDTOs.add(pedidoDTO);
            }


            for (PedidoDTO pro : pedidoDTOs) {
                System.out.println(pro.getTipoEntregaPedido().getDescripcion());
                System.out.println(pro.getTipoPagoPedido().getDescripcion());
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
            PedidoDTO pedidoDTO = new PedidoDTO();

            pedidoDTO.setId(pedido.getId());
            pedidoDTO.setFecha(pedido.getFecha());
            pedidoDTO.setHoraEstimadaFin(pedido.getHoraEstimadaFin());
            pedidoDTO.setMontoDescuento(pedido.getMontoDescuento());


            Cliente cliente =
                    clienteRepository.findByPedidoId(pedido.getId());

            ClienteDTO clienteDTO =
                    new ClienteDTO();
            clienteDTO.setId(cliente.getId());
            clienteDTO.setApellido(cliente.getApellido());
            clienteDTO.setNombre(cliente.getNombre());
            clienteDTO.setTelefono(cliente.getTelefono());

            //Domicilio tabla asociada de cliente ver si esta bien, quizas solo tengo que indicar el id del cliente
            /*Domicilio domicilio =
                    domicilioRepository.findByClienteId(cliente.getId());

            DomicilioDTO domicilioDTO =
                    new DomicilioDTO();
            domicilioDTO.setId(domicilio.getId());
            domicilioDTO.setCalle(domicilio.getCalle());
            domicilioDTO.setNumero(domicilio.getNumero());

            clienteDTO.setDomicilio(domicilioDTO);*/

            //????clienteDTO.setUsuario(cliente.getUsuario());

            //Tipo Entrega Pedido

            TipoEntregaPedido tipoEntregaPedido = tipoEntregaPedidoRepository.findByPedidoId(pedido.getId());

            TipoEntregaPedidoDTO tipoEntregaPedidoDTO = new TipoEntregaPedidoDTO();
            tipoEntregaPedidoDTO.setId(tipoEntregaPedido.getId());
            tipoEntregaPedidoDTO.setDescripcion(tipoEntregaPedido.getDescripcion());

            //Tipo Pago Pedido

            TipoPagoPedido tipoPagoPedido = tipoPagoPedidoRepository.findByPedidoId(pedido.getId());

            TipoPagoPedidoDTO tipoPagoPedidoDTO = new TipoPagoPedidoDTO();
            tipoPagoPedidoDTO.setId(tipoPagoPedido.getId());
            tipoPagoPedidoDTO.setDescripcion(tipoPagoPedido.getDescripcion());

            pedidoDTO.setCliente(clienteDTO);
            pedidoDTO.setTipoEntregaPedido(tipoEntregaPedidoDTO);
            pedidoDTO.setTipoPagoPedido(tipoPagoPedidoDTO);


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

    private PedidoDTO mapPedidoToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setFecha(pedido.getFecha());
        pedidoDTO.setHoraEstimadaFin(pedido.getHoraEstimadaFin());
        pedidoDTO.setMontoDescuento(pedido.getMontoDescuento());

        Cliente cliente = clienteRepository.findByPedidoId(pedido.getId());
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellido(cliente.getApellido());

        pedidoDTO.setCliente(clienteDTO);

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
