package com.example.fraudcheck.controller;

import com.example.fraudcheck.model.FraudScore;
import com.example.fraudcheck.model.Transaction;
import com.example.fraudcheck.service.FraudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FraudController {

    private final FraudService fraudService;

    //    Followed directions, but with RESTful patterns this should be /score-transactions
    @PostMapping("/api/score-transaction")
    public FraudScore createFraudScore(
            @Valid @RequestBody Transaction transaction
    ) {
        log.info("Checking score transaction for Customer: {}", transaction.customerName());
        return fraudService.checkFraud(transaction);
    }

}
