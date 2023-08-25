package com.project.OrderService.Models.Responses;

import com.project.OrderService.Models.Details.OrderDetails;
import com.project.OrderService.Models.Details.StockDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOrderDetails {
    private String message;
    private OrderDetails orderDetails;
    private StockDetails stockDetails;

}
