package br.com.miniautorizador.domain.services.mappers;

import br.com.miniautorizador.api.request.CreateCardRequest;
import br.com.miniautorizador.domain.repositories.entities.CardEntity;
import br.com.miniautorizador.domain.services.dtos.CardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper extends ConverterMapper<CardDTO, CardEntity> {
}
