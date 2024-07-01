package br.com.miniautorizador.domain.services;

import br.com.miniautorizador.api.request.TransactionRequest;
import br.com.miniautorizador.domain.services.enums.ValidationType;

public interface IValidationStrategy {

    void validationCard(TransactionRequest request);

    ValidationType validationType();
}
