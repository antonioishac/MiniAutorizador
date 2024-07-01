package br.com.miniautorizador.domain.services.exceptions;

import br.com.miniautorizador.domain.services.dtos.CardRespDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCardException extends RuntimeException {

    private final CardRespDTO value;

}
