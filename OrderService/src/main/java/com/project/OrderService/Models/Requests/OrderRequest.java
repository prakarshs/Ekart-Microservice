package com.project.OrderService.Models.Requests;

import com.project.OrderService.External.Objects.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Long orderId;
    private PaymentMode paymentMode;
}
