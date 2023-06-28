package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Cliente;
import com.utn.elbuensaborbackend.entities.Pedido;
import com.utn.elbuensaborbackend.entities.Rubro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {

    @Query(value = "SELECT c.* FROM Cliente c " +
            "INNER JOIN Usuario u ON c.usuario_id = u.id_usuario " +
            "INNER JOIN Rol r ON u.rol_id = r.id_rol " +
            "WHERE r.denominacion IN :roles", nativeQuery = true)
    List<Cliente> findAllClientesByRoles(@Param("roles") List<String> roles);

    @Query(value = "SELECT c.* FROM Cliente c " +
            "INNER JOIN Usuario u ON c.usuario_id = u.id_usuario " +
            "INNER JOIN Rol r ON u.rol_id = r.id_rol " +
            "WHERE c.nombre = :nombre", nativeQuery = true)
    List<Cliente> findAllClientesByName(@Param("nombre") String nombre);

    @Query(value = "SELECT c.* FROM Cliente c " +
            "INNER JOIN Usuario u ON c.usuario_id = u.id_usuario " +
            "INNER JOIN Rol r ON u.rol_id = r.id_rol " +
            "WHERE c.apellido = :apellido", nativeQuery = true)
    List<Cliente> findAllClientesByApellido(@Param("apellido") String apellido);

    @Query(value = "SELECT c.* FROM Cliente c " +
            "INNER JOIN Usuario u ON c.usuario_id = u.id_usuario " +
            "INNER JOIN Rol r ON u.rol_id = r.id_rol " +
            "WHERE c.nombre = :nombre AND c.apellido = :apellido", nativeQuery = true)
    List<Cliente> findAllClientesByNameAndApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);
    Cliente findByPedidoId(@Param("pedidoId") Long pedidoId);
}
