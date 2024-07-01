package br.com.miniautorizador.api.controller;

import br.com.miniautorizador.api.request.CreateCardRequest;
import br.com.miniautorizador.domain.services.ISaveCardService;
import br.com.miniautorizador.domain.services.IVerifyBalanceService;
import br.com.miniautorizador.domain.services.dtos.CardRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
@Tag(name = "cartoes", description = "Gerenciando os cartoes.")
public class CardController {

    private final ISaveCardService saveService;
    private final IVerifyBalanceService balanceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criação de um novo cartão", description = "Cartões", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Card created",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardRespDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"),
            @ApiResponse(
                    responseCode = "422",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardRespDTO.class)))
    })
    public ResponseEntity<CardRespDTO> createCard(@Valid @RequestBody CreateCardRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saveService.saveCard(request));
    }

    @Operation(summary = "Verificar saldo do cartão.", description = "Cartões",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Card Balance"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found")
            })
    @GetMapping("/{numeroCartao}")
    public ResponseEntity<BigDecimal> getBalance(
            @PathVariable("numeroCartao") @Parameter(description = "Número do cartão.", example = "6549873025634501") String cardNumber) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(balanceService.verifyCardBalance(cardNumber));
    }
}
