package com.example.fraudcheck.model;

import java.util.List;

public record Signal(
        SignalType signal,
        boolean potentialFraud,
        List<String> details
) {

    public enum SignalType {
        LOCATION("location"),
        IP_ADDRESS("ipAddress"),
        TRANSACTION("transaction"),
        CARD_DETAILS("cardDetails"),
        CHARGE_AMOUNT("chargeAmount");

        public final String label;

        SignalType(String label) {
            this.label = label;
        }
    }
}
