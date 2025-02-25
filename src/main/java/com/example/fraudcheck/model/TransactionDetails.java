package com.example.fraudcheck.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionDetails(
        @NotBlank(message = "Merchant name is required")
        String merchantName,

        @Valid
        @NotNull(message = "Location is required")
        Location merchantLocation,

        int purchasedItemCount
) {

}
