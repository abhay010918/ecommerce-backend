package com.ecommerce.backend.service.services;

import java.util.Map;

public interface PaymentService {

    Map<String, String> createPaymentIntent(long amount, Long orderId);
}
