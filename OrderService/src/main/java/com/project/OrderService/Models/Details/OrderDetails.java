package com.project.OrderService.Models.Details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetails {
    private String orderMessage;
    private Long orderId;
    private Instant orderTime;
    private String orderStatus;
}
