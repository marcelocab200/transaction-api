package br.com.desafiovagaitau.transaction_api.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.desafiovagaitau.transaction_api.controller.dto.StatisticsDTO;
import br.com.desafiovagaitau.transaction_api.controller.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {
    private static final ArrayList<TransactionDTO> transactionsList = new ArrayList<>();

    public void createTransaction(TransactionDTO transaction) {
        // Adiciona a transação à lista armazenada na memória

        log.info("Enviando transacao de valor " + transaction.getValor() + " e data/hora " + transaction.getDataHora() + "...");
        transactionsList.add(transaction);
        log.info("Transacao salva com sucesso!");
    }

    public void deleteTransactions() {
        // Limpa a lista de transações

        log.info("Deletando transacoes...");
        transactionsList.clear();
        log.info("Transacoes deletadas com sucesso!");
    }

    public StatisticsDTO getStatistics(Integer searchInterval) {
        // Retorna as estatísticas de contagem, soma, média, valor mínimo e máximo das transações ocorridas nos últimos segundos de acordo com o valor do "searchInterval" (valor padrão: 60s)

        log.info("Calculando estatisticas das transacoes ocorridas nos ultimos " + searchInterval + " segundos...");

        ArrayList<Double> statisticsValues = new ArrayList<>();

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC); // Valor de data/hora atuais independente do UTC
        long lastMinute = now.toInstant().toEpochMilli() - searchInterval * 1000; // Valor em milissegundos da data/hora atuais menos a quantidade de segundos do intervalo
        
        ArrayList<TransactionDTO> lastMinuteTransactions = new ArrayList<>(transactionsList.stream()
        .filter(t -> t.getDataHora().toInstant().toEpochMilli() >= lastMinute)
        .collect(Collectors.toList())); // Faz uma comparação e adiciona em uma ArrayList as transações que aconteceram dentro do intervalo

        lastMinuteTransactions.forEach(transaction -> statisticsValues.add(transaction.getValor())); // Adiciona apenas os valores da transação à lista e em seguida coletamos os valores de estatística

        Integer count = statisticsValues.size();
        Double sum = statisticsValues.stream().mapToDouble(Double::doubleValue).sum();
        Double avg = statisticsValues.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        Double min = statisticsValues.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
        Double max = statisticsValues.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);

        log.info("Estatisticas calculadas com sucesso!");

        return new StatisticsDTO(count, sum, avg, min, max);
    }
}