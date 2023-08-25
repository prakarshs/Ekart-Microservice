package com.project.StockService.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "STOCK_DETAILS")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stockId;
    @Column(name = "STOCK_NAME")
    private String stockName;
    @Column(name = "STOCK_PRICE")
    private Long stockPrice;
    @Column(name = "STOCK_QUANTITY")
    private Long stockQuantity;
    @Column(name = "STOCK_TIME")
    private Instant stockTime;
}
