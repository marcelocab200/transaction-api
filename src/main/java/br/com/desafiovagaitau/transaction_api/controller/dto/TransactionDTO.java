package br.com.desafiovagaitau.transaction_api.controller.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDTO {
    @NotNull(message = "O valor da transacao deve ser preenchido!") 
    @Positive(message = "O valor da transacao deve ser positivo!")
    private Double valor;

    @NotNull(message = "A data/hora da transacao deve ser preenchida!") 
    @Past(message = "A transacao nao pode ocorrer no futuro!") 
    private OffsetDateTime dataHora;
}