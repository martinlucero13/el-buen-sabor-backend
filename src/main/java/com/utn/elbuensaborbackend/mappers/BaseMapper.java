package com.utn.elbuensaborbackend.mappers;

import com.utn.elbuensaborbackend.dtos.BaseDTO;
import com.utn.elbuensaborbackend.entities.Base;

import java.util.List;

/**
 * Interfaz para mapear entre Entidades y DTOs de la clase Base.
 *
 * @param <E> Tipo de Entidad que extiende de Base.
 * @param <D> Tipo de DTO que extiende de BaseDTO.
 */
public interface BaseMapper<E extends Base, D extends BaseDTO> {

    /**
     * Convierte una Entidad en su correspondiente DTO.
     *
     * @param source Entidad de la clase Base a convertir.
     * @return DTO de la clase Base correspondiende a la entidad proporcionada.
     */
    D toDTO(E source);

    /**
     * Convierte un DTO en su correspondiente entidad.
     *
     * @param source DTO de la clase Base a convertir.
     * @return Entidad de la clase Base correspondiente al DTO proporcionada.
     */
    E toEntity(D source);

    /**
     * Convierte una lista de entidades en una lista de sus correspondientes DTOs.
     *
     * @param source Lista de las entidades de la clase Base a convertir.
     * @return Lista de DTOs de la clase Base correspondientes a las entidades proporcionadas.
     */
    List<D> toDTOsList(List<E> source);

    /**
     * Convierte una lista de DTOs en una lista de sus correspondientes entidades.
     *
     * @param source Lista de las DTOs de la clase Base a convertir.
     * @return Lista de entidades de la clase Base correspondientes a los DTOs proporcionados.
     */
    List<E> toEntitiesList(List<D> source);
}
