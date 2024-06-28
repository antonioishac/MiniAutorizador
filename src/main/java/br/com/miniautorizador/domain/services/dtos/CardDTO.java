package br.com.miniautorizador.domain.services.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDTO {

    private Long id;

    @JsonProperty("numeroCartao")
    @Schema(name = "numeroCartao", description = "Número do cartão.", example = "6549873025634501")
    private String numeroCartao;

    @JsonProperty("valor")
    @Schema(name = "valor", description = "Valor do benefício.", type = "decimal", example = "500.00")
    private BigDecimal valor;

    @JsonProperty("senha")
    @Schema(name = "senha", description = "Senha do cartão", example = "1234")
    private String senha;

}
