package com.project.PaymentService.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "PAYMENT_DETAILS")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    @Column(name = "ORDER_ID")
    private Long orderId;
    @Column(name = "PAYMENT_AMOUNT")
    private Long paymentAmount;
    @Column(name = "PAYMENT_MODE")
    private String paymentMode;
    @Column(name = "PAYMENT_TIME")
    private Instant paymentTime;
    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;
    @Column(name = "PAYMENT_CODE")
    private String paymentReferenceCode;
}
