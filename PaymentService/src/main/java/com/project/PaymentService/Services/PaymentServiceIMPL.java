package com.project.PaymentService.Services;

import com.project.PaymentService.Entities.Payment;
import com.project.PaymentService.Errors.CustomError;
import com.project.PaymentService.Models.PaymentRequest;
import com.project.PaymentService.Models.PaymentResponse;
import com.project.PaymentService.Repositories.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Log4j2
public class PaymentServiceIMPL implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public PaymentResponse makePayment(PaymentRequest paymentRequest) {
        log.info("CREATING PAYMENT..");
        Payment payment = Payment.builder()
                .orderId(paymentRequest.getOrderId())
                .paymentReferenceCode(UUID.randomUUID().toString())
                .paymentAmount(paymentRequest.getPaymentAmount())
                .paymentMode(paymentRequest.getPaymentMode().toString())
                .paymentTime(Instant.now())
                .paymentStatus("SUCCESS")
                .build();
        paymentRepository.save(payment);
        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .orderId(payment.getOrderId())
                .paymentReferenceCode(payment.getPaymentReferenceCode())
                .paymentAmount(payment.getPaymentAmount())
                .paymentMode(payment.getPaymentMode())
                .paymentTime(payment.getPaymentTime())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    @Override
    public PaymentResponse show(Long paymentId) {
        log.info("CHECKING PAYMENT-ID");
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()->new CustomError("PaymentId Does Not Exist","Try With Another PaymentId"));
        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .orderId(payment.getOrderId())
                .paymentReferenceCode(payment.getPaymentReferenceCode())
                .paymentAmount(payment.getPaymentAmount())
                .paymentMode(payment.getPaymentMode())
                .paymentTime(payment.getPaymentTime())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}
