package br.com.miniautorizador.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionRequest {

    @JsonProperty("numeroCartao")
    @Schema(name = "numeroCartao", description = "Número do cartão do beneficiário", example = "6549873025634501")
    private String cardNumber;

    @JsonProperty("senhaCartao")
    @Schema(name = "senhaCartao", description = "Número da senha do cartão do beneficiário", example = "1234")
    private String cardPassword;

    @JsonProperty("valor")
    @Schema(name = "valor", description = "Valor do benefício.", type = "decimal", example = "500.00")
    private BigDecimal balance;


}
