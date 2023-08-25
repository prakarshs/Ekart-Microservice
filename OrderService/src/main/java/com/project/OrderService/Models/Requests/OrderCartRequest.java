package com.project.OrderService.Models.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCartRequest {
    private Long stockId;
    private Long orderQuantity;
}
