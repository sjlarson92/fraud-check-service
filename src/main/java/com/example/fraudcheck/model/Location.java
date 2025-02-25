package com.example.fraudcheck.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Location(
        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        @Size(min = 2, max = 2, message = "State must be exactly 2 characters")
        String state
) {
}
