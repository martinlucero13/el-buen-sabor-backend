package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoDTO;
import com.utn.elbuensaborbackend.dtos.DetallePedidoDTO;
import com.utn.elbuensaborbackend.entities.DetallePedido;
import com.utn.elbuensaborbackend.repositories.DetallePedidoRepository;
import com.utn.elbuensaborbackend.services.interfaces.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetalePedidoServiceImpl  implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ArticuloManufacturadoServiceImpl articuloManufacturadoServiceImpl;

    public List<DetallePedidoDTO> findByPedidoId(Long pedidoId) throws Exception {
        try {
            List<DetallePedido> detallePedidos = detallePedidoRepository.findByPedidoId(pedidoId);
            List<DetallePedidoDTO> detallePedidoDTO = new ArrayList<>();
            for (DetallePedido dp : detallePedidos) {
                DetallePedidoDTO dpDto = new DetallePedidoDTO();
                dpDto.setId(dp.getId());
                dpDto.setCantidad(dp.getCantidad());
                dpDto.setSubTotal(dp.getSubtotal());
                ArticuloManufacturadoDTO articuloManufacturado = articuloManufacturadoServiceImpl.findById(dp.getArticuloManufacturado().getId());
                dpDto.setArticuloManufacturado(articuloManufacturado);
                detallePedidoDTO.add(dpDto);
            }
            return detallePedidoDTO;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
