package com.example.fraudcheck.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Transaction(
        @NotBlank(message = "Customer name is required")
        String customerName,

        @NotBlank(message = "IP address is required")
        String ipAddress,

        @Valid
        @NotNull(message = "Location is required")
        Location location,

        @Valid
        @NotNull(message = "Payment details are required")
        PaymentDetails paymentDetails,

        @Valid
        @NotNull(message = "Transaction details are required")
        TransactionDetails transactionDetails
){}






