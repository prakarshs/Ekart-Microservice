package com.project.StockService.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockRequest {
    private String stockName;
    private Long stockPrice;
    private Long stockQuantity;
}
