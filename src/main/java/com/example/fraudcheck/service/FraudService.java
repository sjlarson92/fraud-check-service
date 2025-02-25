package com.example.fraudcheck.service;

import com.example.fraudcheck.model.FraudScore;
import com.example.fraudcheck.model.Signal;
import com.example.fraudcheck.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FraudService {

    private final LocationService locationService;
    private final IPAddressService ipAddressService;
    private final TransactionService transactionService;
    private final CardDetailsService cardDetailsService;

    public FraudScore checkFraud(Transaction transaction) {
        List<Signal> signals = new ArrayList<>();

        Signal locationSignal = locationService.getLocationSignal(transaction);
        signals.add(locationSignal);

        Signal ipAddressSignal = ipAddressService.getIPAddressSignal(transaction);
        signals.add(ipAddressSignal);

        Signal transactionSignal = transactionService.getTransactionSignal(transaction);
        signals.add(transactionSignal);

        Signal cardDetailsSignal = cardDetailsService.getCardDetailsSignal(transaction);
        signals.add(cardDetailsSignal);

        return new FraudScore(signals);
    }
}
