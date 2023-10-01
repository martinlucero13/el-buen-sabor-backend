package com.utn.elbuensaborbackend.repositories;

import com.utn.elbuensaborbackend.entities.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Interfaz base para los repositorios que manejan entidades que extienden de Base.
 *
 * @param <E> Tipo de Entidad que extiende de Base.
 * @param <ID> Tipo de ID que implementa Serializable.
 */
@NoRepositoryBean
public interface BaseRepository<E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {
}
