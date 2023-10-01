package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {

    @Query(value = "SELECT c.* FROM cliente c " +
            "INNER JOIN usuario u ON c.usuario_id = u.id_usuario " +
            "INNER JOIN rol r ON u.rol_id = r.id_rol " +
            "WHERE r.id_rol <> 5", nativeQuery = true)
    List<Cliente> findAllClientesWithRolEmpleado();

    @Query(value = "SELECT c.* FROM cliente c " +
            "INNER JOIN usuario u ON c.usuario_id = u.id_usuario " +
            "INNER JOIN rol r ON u.rol_id = r.id_rol " +
            "WHERE r.id_rol = 5", nativeQuery = true)
    List<Cliente> findAllClientesWithRolCliente();

    @Query(value = "SELECT c.* FROM cliente c " +
            "INNER JOIN usuario u ON c.usuario_id = u.id_usuario " +
            "INNER JOIN rol r ON u.rol_id = r.id_rol " +
            "WHERE u.auth0_id = :auth0Id", nativeQuery = true)
    Cliente findClienteByUsuarioAuth0Id(@Param("auth0Id") String auth0Id);
}
