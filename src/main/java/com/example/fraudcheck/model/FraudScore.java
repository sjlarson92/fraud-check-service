package com.example.fraudcheck.model;

import java.util.List;

public record FraudScore(
        List<Signal> signals
){}
