package br.com.miniautorizador.api.mock;

import br.com.miniautorizador.api.request.TransactionRequest;

import java.math.BigDecimal;

public class TransactionCardMock {

    public static TransactionRequest transactionRequestMock() {
        return TransactionRequest.builder()
                .cardNumber("2122232425262728")
                .cardPassword("1234")
                .balance(new BigDecimal("10"))
                .build();
    }

    public static TransactionRequest transactionInsufficientBalanceRequestMock() {
        return TransactionRequest.builder()
                .cardNumber("2122232425262728")
                .cardPassword("1234")
                .balance(new BigDecimal("1000"))
                .build();
    }

    public static TransactionRequest transactionPasswordErrorRequestMock() {
        return TransactionRequest.builder()
                .cardNumber("2122232425262728")
                .cardPassword("123456")
                .balance(new BigDecimal("500"))
                .build();
    }
}
