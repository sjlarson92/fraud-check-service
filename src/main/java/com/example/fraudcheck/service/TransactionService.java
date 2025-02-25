package com.example.fraudcheck.service;

import com.example.fraudcheck.model.Signal;
import com.example.fraudcheck.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    public Signal getTransactionSignal(Transaction transaction) {

        List<String> transactionDetails = new ArrayList<>();
        boolean transactionPotentialFraud = transaction.transactionDetails()
                .merchantName()
                .equalsIgnoreCase("Fraud");

        transactionDetails.add(transactionPotentialFraud ?
                "Merchant name is potentially fraudulent" :
                "Transaction details do not look fraudulent"
        );

        return new Signal(
                Signal.SignalType.TRANSACTION,
                transactionPotentialFraud,
                transactionDetails
        );
    }
}
