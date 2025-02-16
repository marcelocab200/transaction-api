package br.com.desafiovagaitau.transaction_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiovagaitau.transaction_api.controller.dto.StatisticsDTO;
import br.com.desafiovagaitau.transaction_api.controller.dto.TransactionDTO;
import br.com.desafiovagaitau.transaction_api.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@Validated
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transacao")
    @Operation(description = "Endpoint responsável por salvar a transação na memória")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transação enviada com sucesso."),
        @ApiResponse(responseCode = "422", description = "Os campos da transação não foram preenchidos corretamente."),
        @ApiResponse(responseCode = "400", description = "Erro de requisição."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<Void> transactionPost(@RequestBody(required = true) @Valid TransactionDTO transaction) {
        transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/transacao")
    @Operation(description = "Endpoint responsável por limpar as transações da memória")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso."),
        @ApiResponse(responseCode = "400", description = "Erro de requisição."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<Void> transactionDelete() {
        transactionService.deleteTransactions();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/estatistica")
    @Operation(description = "Endpoint que retorna as estatísticas de contagem, soma, média, valor mínimo e máximo das transações em um determinado intervalo. O intervalo padrão é 60 segundos, porém é configurável por meio do parâmetro da requisição 'intervaloBusca'. Caso não haja transações no intervalo, os valores serão retornados zerados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estatísticas retornadas com sucesso."),
        @ApiResponse(responseCode = "400", description = "Erro de requisição.", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = @Content)
    })
    public ResponseEntity<StatisticsDTO> transactionGet(@RequestParam(required = false, value = "intervaloBusca", defaultValue = "60") Integer searchInterval) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getStatistics(searchInterval));
    }
}