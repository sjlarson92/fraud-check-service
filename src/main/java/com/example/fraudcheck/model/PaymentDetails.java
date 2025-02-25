package com.example.fraudcheck.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record PaymentDetails(
        @Digits(integer = 4, fraction = 0, message = "CardLast4 must be exactly 4 characters")
        int cardLast4,

        //    followed instructions but should this field be nameOnCard?
        @NotBlank(message = "Name on card is required")
        String cardOnCard,
        BigDecimal cardAmount
) {
}
