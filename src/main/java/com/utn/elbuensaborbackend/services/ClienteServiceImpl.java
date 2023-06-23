package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.*;
import com.utn.elbuensaborbackend.entities.Cliente;
import com.utn.elbuensaborbackend.entities.Domicilio;
import com.utn.elbuensaborbackend.entities.Usuario;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.ClienteMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.ClienteRepository;
import com.utn.elbuensaborbackend.services.interfaces.ClienteService;
import com.utn.elbuensaborbackend.services.interfaces.DomicilioService;
import com.utn.elbuensaborbackend.services.interfaces.UsuarioService;
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
    private UsuarioService usuarioService;

    @Autowired
    private DomicilioService domicilioService;

    private final ClienteMapper clienteMapper = ClienteMapper.getInstance();

    public ClienteServiceImpl(BaseRepository<Cliente, Long> baseRepository, BaseMapper<Cliente, ClienteDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Override
    public List<ClienteDTO> findAllClientesByRoles(List<String> roles) throws Exception {
        try {
            List<Cliente> clientes = clienteRepository.findAllClientesByRoles(roles);
            return clienteMapper.toDTOsList(clientes);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ClienteDTO> findAllClientesByName(String nombre) throws Exception {
        try{
            List<Cliente> clientes = clienteRepository.findAllClientesByName(nombre);
            return clienteMapper.toDTOsList(clientes);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ClienteDTO> findAllClientesByApellido(String apellido) throws Exception {
        try{
            List<Cliente> clientes = clienteRepository.findAllClientesByApellido(apellido);
            return clienteMapper.toDTOsList(clientes);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ClienteDTO> findAllClientesByNameAndApellido(String nombre, String apellido) throws Exception {
        try{
            List<Cliente> clientes = clienteRepository.findAllClientesByNameAndApellido(nombre, apellido);
            return clienteMapper.toDTOsList(clientes);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Cliente saveCliente(ClienteDTO dto) throws Exception {
        try {
            Cliente cliente = clienteMapper.toEntity(dto);

            UsuarioDTO usuarioDTO = dto.getUsuario();
            if (usuarioDTO != null && usuarioDTO.getId() == null) {
                Usuario usuario = usuarioService.save(usuarioDTO);
                cliente.setUsuario(usuario);
            }

            DomicilioDTO domicilioDTO = dto.getDomicilio();
            if (domicilioDTO != null && domicilioDTO.getId() == null) {
                Domicilio domicilio = domicilioService.save(domicilioDTO);
                cliente.setDomicilio(domicilio);
            }

            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Cliente updateCliente(Long id, ClienteDTO dto) throws Exception {
        try {
            Optional<Cliente> optional = clienteRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Cliente a modificar no existe.");
            }

            Cliente cliente = optional.get();

            UsuarioDTO usuarioDTO = dto.getUsuario();
            if (usuarioDTO.getId() == null) {
                Usuario usuario = usuarioService.save(usuarioDTO);
                cliente.setUsuario(usuario);
            } else {
                Usuario usuario = usuarioService.update(id, usuarioDTO);
                cliente.setUsuario(usuario);
            }

            DomicilioDTO domicilioDTO = dto.getDomicilio();
            if (domicilioDTO.getId() == null) {
                Domicilio domicilio = domicilioService.save(domicilioDTO);
                cliente.setDomicilio(domicilio);
            } else {
                Domicilio domicilio = domicilioService.update(domicilioDTO.getId(), domicilioDTO);
                cliente.setDomicilio(domicilio);
            }

            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}