package com.project.StockService.Services;

import com.project.StockService.Models.StockRequest;
import com.project.StockService.Models.StockResponse;

public interface StockService {
    StockResponse addStock(StockRequest stockRequest);

    StockResponse reduceStock(Long stockId, Long stockQuantity);

    StockResponse showStock(Long stockId);
}
