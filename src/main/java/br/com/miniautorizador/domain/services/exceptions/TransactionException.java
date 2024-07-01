package br.com.miniautorizador.domain.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionException extends RuntimeException{

    private final String message;
}
