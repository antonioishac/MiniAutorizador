package br.com.miniautorizador.api.handler;

import br.com.miniautorizador.api.request.CreateCardRequest;
import br.com.miniautorizador.domain.services.dtos.CardRespDTO;
import br.com.miniautorizador.domain.services.exceptions.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VerifyBalanceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Void> handleCardNotFoundException(VerifyBalanceException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VerifyExistsCardException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> handleNotExistsCardException(VerifyExistsCardException ex) {
        return new ResponseEntity<>("CARTAO_INEXISTENTE", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(VerifyPasswordCardException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> handlePasswordNotCorrectException(VerifyPasswordCardException ex) {
        return new ResponseEntity<>("SENHA_INVALIDA", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(VerifySufficientBalanceException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> handleInsufficientBalanceException(VerifySufficientBalanceException ex) {
        return new ResponseEntity<>("SALDO_INSUFICIENTE", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CreateCardException.class)
    public ResponseEntity<CardRespDTO> handleCreateCardException(CreateCardException exception) {
        return ResponseEntity.unprocessableEntity().body(exception.getValue());
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<String> handleTransactionalException(TransactionException exception) {
        return ResponseEntity.unprocessableEntity().body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        var messages = new ArrayList<String>();

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fes = bindingResult.getFieldErrors();

        for (FieldError fe : fes) {
            String defaultMessage = fe.getDefaultMessage();
            if (StringUtils.isNotBlank(defaultMessage)) {
                messages.add(defaultMessage);
            }
        }
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }
}
