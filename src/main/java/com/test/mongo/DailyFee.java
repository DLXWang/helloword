package com.test.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("spot_daily_fee")
public class DailyFee {

    private String exchangeId;

    private String symbol;

    private LocalDateTime dayTime;

    private BigDecimal totalFee;

    private Long selectedAccountId;
}
