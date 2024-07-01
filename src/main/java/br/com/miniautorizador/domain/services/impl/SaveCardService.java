package br.com.miniautorizador.domain.services.impl;

import br.com.miniautorizador.api.request.CreateCardRequest;
import br.com.miniautorizador.domain.repositories.CardRepository;
import br.com.miniautorizador.domain.services.ISaveCardService;
import br.com.miniautorizador.domain.services.dtos.CardRespDTO;
import br.com.miniautorizador.domain.services.exceptions.CreateCardException;
import br.com.miniautorizador.domain.services.mappers.CardMapper;
import br.com.miniautorizador.domain.services.mappers.CardRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SaveCardService implements ISaveCardService {

    private static final BigDecimal INITIAL_VALUE = BigDecimal.valueOf(500.00);

    private final CardRepository repository;
    private final CardRequestMapper saveMapper;
    private final CardMapper mapper;

    @Override
    public CardRespDTO saveCard(CreateCardRequest request) {

        try {
            var card = saveMapper.toEntity(request);
            card.setCardBalance(INITIAL_VALUE);

            var saveCard = repository.save(card);
            return mapper.toDto(saveCard);
        } catch (Exception ex) {
            var resError = CardRespDTO.builder()
                    .password(request.getPassword())
                    .cardNumber(request.getCardNumber())
                    .build();

            throw new CreateCardException(resError);
        }
    }
}
