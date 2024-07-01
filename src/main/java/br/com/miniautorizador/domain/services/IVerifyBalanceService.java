package br.com.miniautorizador.domain.services;

import java.math.BigDecimal;

public interface IVerifyBalanceService {

    BigDecimal verifyCardBalance(String cardNumber);
}
