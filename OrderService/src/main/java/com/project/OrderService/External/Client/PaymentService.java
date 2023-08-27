package com.project.OrderService.External.Client;

import com.project.OrderService.External.Objects.PaymentRequest;
import com.project.OrderService.External.Objects.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE/payments")
public interface PaymentService {
    @PostMapping("/make")
    ResponseEntity<PaymentResponse> make(@RequestBody PaymentRequest paymentRequest);
}
