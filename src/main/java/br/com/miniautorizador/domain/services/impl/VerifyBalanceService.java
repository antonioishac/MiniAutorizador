package br.com.miniautorizador.domain.services.impl;

import br.com.miniautorizador.domain.repositories.CardRepository;
import br.com.miniautorizador.domain.services.IVerifyBalanceService;
import br.com.miniautorizador.domain.services.exceptions.VerifyBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class VerifyBalanceService implements IVerifyBalanceService {

    private final CardRepository repository;

    @Override
    public BigDecimal verifyCardBalance(String cardNumber) {

        var balance = repository.findCardByBalance(cardNumber)
                .orElseThrow(VerifyBalanceException::new);

        return balance.getCardBalance();
    }
}
