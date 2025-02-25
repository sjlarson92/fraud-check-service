package com.example.fraudcheck.model;

public record Transaction(
        String customerName,
        String ipAddress,
        Location location,
        PaymentDetails paymentDetails,
        TransactionDetails transactionDetails
){}






