package br.com.miniautorizador.api.mock;

import br.com.miniautorizador.api.request.CreateCardRequest;

public class SaveCardMock {

    public static CreateCardRequest createCardRequest() {
        return CreateCardRequest.builder()
                .cardNumber("2122232425262728")
                .password("1234")
                .build();
    }
}
