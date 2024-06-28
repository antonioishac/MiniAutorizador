package br.com.miniautorizador.domain.services;

import br.com.miniautorizador.api.request.CreateCardRequest;
import br.com.miniautorizador.domain.services.dtos.CardDTO;

public interface ICardService {

    CardDTO saveCard(CreateCardRequest request);
}
