package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

    @Query(value = "SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findByEmail(@Param("email") String email);
}
