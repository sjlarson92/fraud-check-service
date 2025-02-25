package com.example.fraudcheck.service;

import com.example.fraudcheck.model.Signal;
import com.example.fraudcheck.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardDetailsService {
    public Signal getCardDetailsSignal(Transaction transaction) {
        List<String> cardDetails = new ArrayList<>();
        boolean cardDetailsPotentialFraud = !transaction.paymentDetails().cardOnCard().equalsIgnoreCase(transaction.customerName());

        cardDetails.add(cardDetailsPotentialFraud
                ? "Card details look fraudulent"
                : "Card details do not look fraudulent"
        );

        return new Signal(Signal.SignalType.CARD_DETAILS, cardDetailsPotentialFraud, cardDetails);
    }
}
