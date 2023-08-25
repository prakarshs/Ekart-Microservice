package com.project.OrderService.External.Client;

import com.project.OrderService.External.Objects.StockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "STOCK-SERVICE",url = "http://localhost:8080/stocks")
public interface StockService {
    @PutMapping("/reduce")
     ResponseEntity<StockResponse> reduce(@RequestParam("id") Long stockId, @RequestParam("quantity") Long stockQuantity);
}
