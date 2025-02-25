package com.example.fraudcheck.service;

import com.example.fraudcheck.model.Signal;
import com.example.fraudcheck.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LocationService {
    public Signal getLocationSignal(Transaction transaction) {

        boolean locationPotentialFraud = !Objects.equals(transaction.location().state(), transaction.transactionDetails().merchantLocation().state());
        List<String> locationDetails = new ArrayList<>();

        locationDetails.add(locationPotentialFraud
                ? "Location differs from resident location"
                : "Location details do not look fraudulent");

        return new Signal(Signal.SignalType.LOCATION, locationPotentialFraud, locationDetails);
    }
}
