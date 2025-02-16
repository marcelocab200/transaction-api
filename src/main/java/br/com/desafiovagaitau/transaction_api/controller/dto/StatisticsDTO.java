package br.com.desafiovagaitau.transaction_api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatisticsDTO {
    private Integer count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;
}
