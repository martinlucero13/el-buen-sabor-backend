package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
    @Query(value = "SELECT usuario.* FROM usuario " +
            "INNER JOIN cliente ON usuario.id_usuario=cliente.usuario_id " +
            "WHERE cliente.id_cliente= :clienteId", nativeQuery = true)
    Usuario findByClienteId(@Param("clienteId") Long clienteId);

    @Query(value = "SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findByEmail(@Param("email") String email);

    @Query(value = "SELECT COUNT(u) FROM Usuario u WHERE u.rol.id = :rolId")
    Integer findCantidadByRol(@Param("rolId") Long rolId);
}
