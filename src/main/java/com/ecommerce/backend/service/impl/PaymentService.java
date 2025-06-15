package com.ecommerce.backend.service.impl;

import java.util.Map;

public interface PaymentService {

    Map<String, String> createPaymentIntent(long amount, Long orderId);
}
