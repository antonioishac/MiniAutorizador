package br.com.miniautorizador.domain.services.impl;

import br.com.miniautorizador.api.request.TransactionRequest;
import br.com.miniautorizador.domain.repositories.CardRepository;
import br.com.miniautorizador.domain.services.IValidationStrategy;
import br.com.miniautorizador.domain.services.enums.ValidationType;
import br.com.miniautorizador.domain.services.exceptions.VerifyPasswordCardException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerifyPasswordCardService implements IValidationStrategy {

    private final CardRepository repository;

    @Override
    public void validationCard(TransactionRequest request) {
        repository.findByCardNumberAndPassword(request.getCardNumber(), request.getCardPassword())
                .orElseThrow(VerifyPasswordCardException::new);
    }

    @Override
    public ValidationType validationType() {
        return ValidationType.PASSWORD;
    }
}
