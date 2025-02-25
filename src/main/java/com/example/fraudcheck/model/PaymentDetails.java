package com.example.fraudcheck.model;

import java.math.BigDecimal;

public record PaymentDetails(
        int cardLast4,
        //    followed instructions but should this field be nameOnCard?
        String cardOnCard,
        BigDecimal cardAmount
) {
}
