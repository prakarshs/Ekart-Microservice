package com.project.PaymentService.Services;

import com.project.PaymentService.Models.PaymentRequest;
import com.project.PaymentService.Models.PaymentResponse;

public interface PaymentService {
    PaymentResponse makePayment(PaymentRequest paymentRequest);

    PaymentResponse show(Long paymentId);
}
