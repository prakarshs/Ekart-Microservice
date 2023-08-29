package com.project.OrderService.External.Client;

import com.project.OrderService.Errors.CustomError;
import com.project.OrderService.External.Objects.PaymentRequest;
import com.project.OrderService.External.Objects.PaymentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "PAYMENT-SERVICE/payments")
public interface PaymentService {
    @PostMapping("/make")
    ResponseEntity<PaymentResponse> make(@RequestBody PaymentRequest paymentRequest);

}
