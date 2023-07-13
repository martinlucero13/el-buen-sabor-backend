package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Domicilio;
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
}
