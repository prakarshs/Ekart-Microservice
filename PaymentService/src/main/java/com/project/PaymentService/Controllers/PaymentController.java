package com.project.PaymentService.Controllers;

import com.project.PaymentService.Models.PaymentRequest;
import com.project.PaymentService.Models.PaymentResponse;
import com.project.PaymentService.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/make")
    public ResponseEntity<PaymentResponse> make(@RequestBody PaymentRequest paymentRequest)
    {
        return new ResponseEntity<>(paymentService.makePayment(paymentRequest), HttpStatus.OK);
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<PaymentResponse> show(@PathVariable("id") Long paymentId)
    {
        return new ResponseEntity<>(paymentService.show(paymentId), HttpStatus.OK);
    }
}
