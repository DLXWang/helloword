package com.test.controller.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResultType {
    private String symbol;
    private BigDecimal result1;
    private Long result2;
}
