package br.com.miniautorizador.domain.services.mappers;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface ConverterMapper<D, E> {

    E toEntity(D dto);
    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);
    List<D> toDto(List<E> entityList);

    E updateEntity(@MappingTarget E entity, D dto );

    D updateDto(@MappingTarget D dto , E entity);
}
