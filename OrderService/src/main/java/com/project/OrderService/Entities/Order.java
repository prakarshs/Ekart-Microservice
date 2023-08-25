package com.project.OrderService.Entities;

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
@Table(name = "ORDER_DETAILS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @Column(name = "STOCK_ID")
    private Long stockId;
    @Column(name = "ORDER_QUANTITY")
    private Long orderQuantity;
    @Column(name = "ORDER_AMOUNT")
    private Long orderAmount;
    @Column(name = "ORDER_TIME")
    private Instant orderTime;
    @Column(name = "ORDER_STATUS")
    private String orderStatus;
}
