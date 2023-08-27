package com.project.OrderService.External.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Long paymentId;
    private Long orderId;
    private Long paymentAmount;
    private String paymentMode;
    private Instant paymentTime;
    private String paymentStatus;
    private String paymentReferenceCode;
}
