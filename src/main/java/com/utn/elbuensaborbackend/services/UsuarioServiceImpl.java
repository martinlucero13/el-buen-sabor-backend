package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.RolDTO;
import com.utn.elbuensaborbackend.dtos.UsuarioDTO;
import com.utn.elbuensaborbackend.entities.Rol;
import com.utn.elbuensaborbackend.entities.Usuario;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.UsuarioMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.UsuarioRepository;
import com.utn.elbuensaborbackend.services.interfaces.RolService;
import com.utn.elbuensaborbackend.services.interfaces.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, UsuarioDTO, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolService rolService;

    private UsuarioMapper usuarioMapper = UsuarioMapper.getInstance();

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository, BaseMapper<Usuario, UsuarioDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Transactional
    public Usuario saveUsuario(UsuarioDTO dto) throws Exception {
        try {
            Usuario usuario = usuarioMapper.toEntity(dto);

            RolDTO rolDTO = dto.getRol();
            if (rolDTO != null && rolDTO.getId() == null) {
                Rol rol = rolService.save(rolDTO);
                usuario.setRol(rol);
            }

            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Usuario updateUsuario(Long id, UsuarioDTO dto) throws Exception {
        try {
            Optional<Usuario> optional = usuarioRepository.findById(id);

            if (optional.isEmpty()) {
                throw new Exception("El Usuario a actualizar no existe.");
            }

            Usuario usuario = optional.get();

            RolDTO rolDTO = dto.getRol();
            if (rolDTO.getId() == null) {
                Rol rol = rolService.save(rolDTO);
                usuario.setRol(rol);
            } else {
                Rol rol = rolService.update(rolDTO.getId(), rolDTO);
                usuario.setRol(rol);
            }

            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
