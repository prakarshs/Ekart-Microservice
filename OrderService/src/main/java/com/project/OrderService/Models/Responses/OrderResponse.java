package com.project.OrderService.Models.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String message;
    private Long orderId;
    private Instant orderTime;
    private Long orderAmount;
    private String orderStatus;
}
