package br.com.miniautorizador.domain.services;

import br.com.miniautorizador.api.request.CreateCardRequest;
import br.com.miniautorizador.domain.services.dtos.CardRespDTO;

public interface ISaveCardService {

    CardRespDTO saveCard(CreateCardRequest request);
}
