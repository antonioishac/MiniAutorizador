package br.com.miniautorizador.api.controller;

import br.com.miniautorizador.api.request.TransactionRequest;
import br.com.miniautorizador.domain.services.ITransactionService;
import io.swagger.v3.oas.annotations.Operation;
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

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
@Tag(name = "transacoes", description = "Transações dos cartoes.")
public class TransactionController {

    private static final String SUCCESS = "OK";
    private final ITransactionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Transação do cartão do cliente.", description = "Transação", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "OK"),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable Entity",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class /*ResponseErrorDTO.class*/))),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class /*ResponseErrorDTO.class*/)))
    })
    public ResponseEntity<String> transactionalCard(@Valid @RequestBody TransactionRequest request) {

        service.transactionCard(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SUCCESS);
    }
}
