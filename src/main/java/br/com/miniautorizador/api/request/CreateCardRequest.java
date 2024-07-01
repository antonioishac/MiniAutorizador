package br.com.miniautorizador.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCardRequest {

    @NotNull(message = "O número do cartão não pode ser nulo.")
    @NotEmpty(message = "O número do cartão não pode ser vazio.")
    @JsonProperty("numeroCartao")
    @Schema(name = "numeroCartao", description = "Número do cartão do beneficiário", example = "6549873025634501")
    private String cardNumber;

    @NotNull(message = "A senha não pode ser nula.")
    @NotEmpty(message = "A senha não pode ser vazia.")
    @JsonProperty("senha")
    @Schema(name = "senha", description = "Número da senha do cartão do beneficiário", example = "1234")
    private String password;
}
