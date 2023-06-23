package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
}
