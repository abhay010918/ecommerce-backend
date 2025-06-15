package com.ecommerce.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestEvent {

    private String orderId;
    private String productName;
    private Long amount;
    private String currency;
    private String customerEmail;

}
