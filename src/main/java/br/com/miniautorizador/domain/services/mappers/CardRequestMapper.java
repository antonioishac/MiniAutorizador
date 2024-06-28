package br.com.miniautorizador.domain.services.mappers;

import br.com.miniautorizador.api.request.CreateCardRequest;
import br.com.miniautorizador.domain.repositories.entities.CardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardRequestMapper extends ConverterMapper<CreateCardRequest, CardEntity> {
}
