package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.UsuarioDTO;
import com.utn.elbuensaborbackend.entities.Usuario;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.UsuarioMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.UsuarioRepository;
import com.utn.elbuensaborbackend.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, UsuarioDTO, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioMapper usuarioMapper = UsuarioMapper.getInstance();

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository, BaseMapper<Usuario, UsuarioDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Override
    public Boolean existsByEmail(String email) throws Exception {
        try {
            Usuario usuario = usuarioRepository.findByEmail(email);
            return usuario != null ? true : false;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
