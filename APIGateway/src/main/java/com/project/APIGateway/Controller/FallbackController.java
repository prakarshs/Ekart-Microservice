package com.project.APIGateway.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/orderServiceFallback")
    public ResponseEntity<String> orderfallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Order Service Is Not Available. Please Try Again Later.");
    }

    @GetMapping("/stockServiceFallback")
    public ResponseEntity<String> stockfallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Stock Service Is Not Available. Please Try Again Later.");
    }

    @GetMapping("/paymentServiceFallback")
    public ResponseEntity<String> paymentfallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Payment Service Is Not Available. Please Try Again Later.");
    }

    @GetMapping("/frontendServiceFallback")
    public ResponseEntity<String> frontendfallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Frontend Service Is Not Available. Please Try Again Later.");
    }

    @GetMapping("/userServiceFallback")
    public ResponseEntity<String> userfallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("User Service Is Not Available. Please Try Again Later.");
    }
    }

