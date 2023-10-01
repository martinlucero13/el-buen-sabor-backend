package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.ClienteDTO;
import com.utn.elbuensaborbackend.entities.Cliente;
import com.utn.elbuensaborbackend.entities.Pedido;
import com.utn.elbuensaborbackend.entities.Usuario;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.ClienteMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.ClienteRepository;
import com.utn.elbuensaborbackend.repositories.PedidoRepository;
import com.utn.elbuensaborbackend.services.interfaces.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, ClienteDTO, Long> implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;
    private final ClienteMapper clienteMapper = ClienteMapper.getInstance();

    public ClienteServiceImpl(BaseRepository<Cliente, Long> baseRepository, BaseMapper<Cliente, ClienteDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Override
    public List<ClienteDTO> findAllEmpleados() throws Exception {
        try {
            List<Cliente> clientes = clienteRepository.findAllClientesWithRolEmpleado();
            return clienteMapper.toDTOsList(clientes);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ClienteDTO> findAllClientes() throws Exception {
        try {
            List<Cliente> clientes = clienteRepository.findAllClientesWithRolCliente();
            return clienteMapper.toDTOsList(clientes);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ClienteDTO findClienteByPedido(Long pedidoId) throws Exception {
        try {
            Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);

            if (optionalPedido.isPresent()) {
                Pedido pedido = optionalPedido.get();
                Cliente cliente = pedido.getCliente();

                return clienteMapper.toDTO(cliente);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ClienteDTO findClienteByUsuarioAuth0Id(String auht0Id) throws Exception {
        try {
            Cliente cliente = clienteRepository.findClienteByUsuarioAuth0Id(auht0Id);
            return clienteMapper.toDTO(cliente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ClienteDTO updateEstado(Long id) throws Exception {
        try {
            Optional<Cliente> optional = clienteRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Cliente a actualizar no existe.");
            }

            Cliente cliente = optional.get();
            Usuario usuario = cliente.getUsuario();
            usuario.setBloqueado(!usuario.getBloqueado());
            cliente.setUsuario(usuario);

            return clienteMapper.toDTO(clienteRepository.save(cliente));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}