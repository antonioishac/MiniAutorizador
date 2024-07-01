package br.com.miniautorizador.domain.services.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardRespDTO {

    @JsonProperty("senha")
    @Schema(name = "senha", description = "Senha do cartão", example = "1234")
    private String password;

    @JsonProperty("numeroCartao")
    @Schema(name = "numeroCartao", description = "Número do cartão.", example = "6549873025634501")
    private String cardNumber;

}
