package br.com.miniautorizador.domain.services.impl;

import br.com.miniautorizador.api.request.TransactionRequest;
import br.com.miniautorizador.domain.repositories.CardRepository;
import br.com.miniautorizador.domain.services.ITransactionService;
import br.com.miniautorizador.domain.services.context.ValidationContext;
import br.com.miniautorizador.domain.services.enums.ValidationType;
import br.com.miniautorizador.domain.services.exceptions.TransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private static final String ERROR_TRANSACTION = "Ocorreu um erro na transação";

    private final CardRepository repository;
    private final ValidationContext validationContext;

    @Transactional
    @Override
    public void transactionCard(final TransactionRequest request) {
        validateTransaction(request);

        try {
            repository.updateCardBalance(request.getBalance(), request.getCardNumber());
        } catch (Exception ex) {
            throw new TransactionException(ERROR_TRANSACTION);
        }
    }

    private void validateTransaction(final TransactionRequest request) {
        Stream.of(ValidationType.values()).forEach(type -> validationContext.validation(request, type));
    }
}
