package br.com.miniautorizador.domain.services.impl;

import br.com.miniautorizador.api.request.TransactionRequest;
import br.com.miniautorizador.domain.repositories.CardRepository;
import br.com.miniautorizador.domain.services.IValidationStrategy;
import br.com.miniautorizador.domain.services.enums.ValidationType;
import br.com.miniautorizador.domain.services.exceptions.VerifySufficientBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerifySufficientBalanceService implements IValidationStrategy {

    private final CardRepository repository;

    @Override
    public void validationCard(TransactionRequest request) {
        repository.findByCardNumber(request.getCardNumber())
                .filter(c -> c.getCardBalance().compareTo(request.getBalance()) >= 0)
                .orElseThrow(VerifySufficientBalanceException::new);
    }

    @Override
    public ValidationType validationType() {
        return ValidationType.SUFFICIENT_BALANCE;
    }
}
