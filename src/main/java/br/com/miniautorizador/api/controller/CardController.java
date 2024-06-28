package br.com.miniautorizador.api.controller;

import br.com.miniautorizador.api.request.CreateCardRequest;
import br.com.miniautorizador.domain.services.ICardService;
import br.com.miniautorizador.domain.services.dtos.CardDTO;
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
@RequestMapping("/cartoes")
@RequiredArgsConstructor
@Tag(name = "cartoes", description = "Gerenciando os cartoes.")
public class CardController {

    private final ICardService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criação de um novo cartão", description = "Cartões", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Card created"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class /*ResponseErrorDTO.class*/))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class /*ResponseErrorDTO.class*/)))
    })
    public ResponseEntity<CardDTO> createCard(@Valid @RequestBody CreateCardRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.saveCard(request));
    }
}
