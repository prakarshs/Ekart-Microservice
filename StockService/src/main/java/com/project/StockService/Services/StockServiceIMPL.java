package com.project.StockService.Services;

import com.project.StockService.Entities.Stock;
import com.project.StockService.Errors.CustomError;
import com.project.StockService.Models.StockRequest;
import com.project.StockService.Models.StockResponse;
import com.project.StockService.Repositories.StockRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class StockServiceIMPL implements StockService {

    @Autowired
    private StockRepository stockRepository;
    @Override
    public StockResponse addStock(StockRequest stockRequest) {
        log.info("CREATING STOCK...");
        Stock stock = Stock.builder()
                .stockName(stockRequest.getStockName())
                .stockPrice(stockRequest.getStockPrice())
                .stockQuantity(stockRequest.getStockQuantity())
                .stockTime(Instant.now())
                .build();

        stockRepository.save(stock);

        return StockResponse.builder()
                .message("YOUR STOCK HAS BEEN ADDED.")
                .stockId(stock.getStockId())
                .stockName(stock.getStockName())
                .stockPrice(stock.getStockPrice())
                .stockQuantity(stock.getStockQuantity())
                .stockTime(stock.getStockTime())
                .build();
    }

    @Override
    public StockResponse reduceStock(Long stockId, Long stockQuantity) {
        log.info("CHECKING FOR STOCK-ID...");
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new CustomError("The Stock With Given ID Is Not Present", "Try Entering A Different Id"));
        if(stock!=null){log.info("STOCK FOUND!");}else{log.info("STOCK NOT FOUND.");}
        if (stockQuantity < stock.getStockQuantity()) {
            stock.setStockQuantity(stock.getStockQuantity() - stockQuantity);

            stockRepository.save(stock);
        } else {log.info("QUANTITY INSUFFICIENT");throw new CustomError("We Dont Have Enough Stock", "Try Entering A Lower Amount");}

        return StockResponse.builder()
                .message("YOUR STOCK HAS BEEN REDUCED IN QUANTITY.")
                .stockId(stock.getStockId())
                .stockName(stock.getStockName())
                .stockPrice(stock.getStockPrice())
                .stockQuantity(stock.getStockQuantity())
                .stockTime(stock.getStockTime())
                .build();

    }

    @Override
    public StockResponse showStock(Long stockId) {
        log.info("CHECKING FOR STOCK-ID...");
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new CustomError("The Stock With Given ID Is Not Present", "Try Entering A Different Id"));
        if(stock!=null){log.info("STOCK FOUND!");}else{log.info("STOCK NOT FOUND.");}
        return StockResponse.builder()
                .message("HERE'S YOUR REQUIRED STOCK.")
                .stockId(stock.getStockId())
                .stockName(stock.getStockName())
                .stockPrice(stock.getStockPrice())
                .stockQuantity(stock.getStockQuantity())
                .stockTime(stock.getStockTime())
                .build();
    }
}
