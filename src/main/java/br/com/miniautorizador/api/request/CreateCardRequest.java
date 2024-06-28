package br.com.miniautorizador.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateCardRequest {

    @JsonProperty("numeroCartao")
    @Schema(name = "numeroCartao", description = "Número do cartão do beneficiário", example = "6549873025634501")
    private String numeroCartao;

    @JsonProperty("valor")
    @Schema(name = "valor", description = "Valor do benefício.", type = "decimal", example = "500.00")
    private BigDecimal valor;

    @JsonProperty("senha")
    @Schema(name = "senha", description = "Número da senha do cartão do beneficiário", example = "1234")
    private String senha;
}
