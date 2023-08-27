package com.project.StockService.Services;

import com.project.StockService.Models.StockData;
import com.project.StockService.Models.StockRequest;
import com.project.StockService.Models.StockResponse;

import java.util.List;

public interface StockService {
    StockResponse addStock(StockRequest stockRequest);

    StockResponse reduceStock(Long stockId, Long stockQuantity);

    StockResponse showStock(Long stockId);

    List<StockData> showData();
}
