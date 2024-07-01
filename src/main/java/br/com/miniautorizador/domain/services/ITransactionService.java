package br.com.miniautorizador.domain.services;

import br.com.miniautorizador.api.request.TransactionRequest;

public interface ITransactionService {

    void transactionCard(final TransactionRequest request);
}
