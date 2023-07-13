package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Domicilio;
import com.utn.elbuensaborbackend.entities.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends BaseRepository<Rol, Long> {

    @Query(value = "SELECT rol.* FROM rol " +
            "INNER JOIN usuario ON rol.id_rol=usuario.rol_id " +
            "WHERE usuario.id_usuario= :usuarioId", nativeQuery = true)
    Rol findByUsuarioId(@Param("usuarioId") Long usuarioId);
}
