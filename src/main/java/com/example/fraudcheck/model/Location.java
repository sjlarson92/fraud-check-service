package com.example.fraudcheck.model;

import jakarta.validation.constraints.NotBlank;

public record Location(
        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state
) {
}
