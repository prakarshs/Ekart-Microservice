package com.project.StockService.Controllers;

import com.project.StockService.Models.StockRequest;
import com.project.StockService.Models.StockResponse;
import com.project.StockService.Services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockService stockService;


    @PostMapping("/add")
    public ResponseEntity<StockResponse> add(@RequestBody StockRequest stockRequest){
        return new ResponseEntity<>(stockService.addStock(stockRequest), HttpStatus.OK);
    }

    @PutMapping("/reduce")
    public ResponseEntity<StockResponse> reduce(@RequestParam("id") Long stockId, @RequestParam("quantity") Long stockQuantity){
        return new ResponseEntity<>(stockService.reduceStock(stockId,stockQuantity), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<StockResponse> show(@PathVariable("id") Long stockId){
        return new ResponseEntity<>(stockService.showStock(stockId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        return ResponseEntity.ok().build();
    }
}
