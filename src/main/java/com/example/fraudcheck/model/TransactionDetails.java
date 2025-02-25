package com.example.fraudcheck.model;

public record TransactionDetails(
        String merchantName,
        Location merchantLocation,
        int purchasedItemCount
) {

}
