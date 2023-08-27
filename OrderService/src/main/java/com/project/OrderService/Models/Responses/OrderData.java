package com.project.OrderService.Models.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {
    private Long orderId;
    private String stockNameData;
    private Long orderQuantityData;
    private Long orderAmountData;
    private String orderStatus;

}
