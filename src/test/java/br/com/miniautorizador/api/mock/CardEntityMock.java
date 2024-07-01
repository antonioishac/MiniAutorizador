package br.com.miniautorizador.api.mock;

import br.com.miniautorizador.domain.repositories.entities.CardEntity;

import java.math.BigDecimal;

public class CardEntityMock {

    public static CardEntity cardEntity() {
        return CardEntity.builder()
                .id(1L)
                .cardBalance(new BigDecimal("500.00"))
                .password("1234")
                .cardNumber("2122232425262728").build();
    }
}
