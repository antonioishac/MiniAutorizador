package br.com.miniautorizador.domain.services.impl;

import br.com.miniautorizador.api.request.CreateCardRequest;
import br.com.miniautorizador.domain.repositories.CardRepository;
import br.com.miniautorizador.domain.services.ICardService;
import br.com.miniautorizador.domain.services.dtos.CardDTO;
import br.com.miniautorizador.domain.services.mappers.CardMapper;
import br.com.miniautorizador.domain.services.mappers.CardRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {

    private final CardRepository repository;
    private final CardRequestMapper saveMapper;
    private final CardMapper mapper;

    @Override
    public CardDTO saveCard(CreateCardRequest request) {
        var saveCard = repository.save(saveMapper.toEntity(request));
        return mapper.toDto(saveCard);
    }
}
