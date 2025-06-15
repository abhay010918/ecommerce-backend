package com.ecommerce.backend.controller;

import com.ecommerce.backend.service.impl.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*") // for Angular frontend
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create-payment-intent")
    public ResponseEntity<Map<String, String>> createPaymentIntent(
            @RequestParam long amount,
            @RequestParam Long orderId
    ) {
        Map<String, String> response = paymentService.createPaymentIntent(amount, orderId);
        return ResponseEntity.ok(response);
    }
}
