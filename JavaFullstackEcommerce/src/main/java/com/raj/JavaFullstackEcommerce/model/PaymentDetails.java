package com.raj.JavaFullstackEcommerce.model;

import com.sun.jdi.event.StepEvent;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {
    private  String paymentMethod;

    private String paymentStatus;

    private String paymentId;

    private String razorpayPaymentLinkId;

    private  String razorpayPaymentLinkReferenceId;

    private String razorpayPaymentLinkStatus;

    private String razorpayPaymentId;
}
