package com.example.fraudcheck.service;

import com.example.fraudcheck.model.Signal;
import com.example.fraudcheck.model.Transaction;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class IPAddressService {

    @Value("${ACCOUNT_ID}")
    private int accountId;

    @Value("${LICENSE_KEY}")
    private String licenseKey;

    public Signal getIPAddressSignal(Transaction transaction) {
        List<String> ipAddressDetails = new ArrayList<>();
        try {
            WebServiceClient client = new WebServiceClient.Builder(
                    accountId,
                    licenseKey
            )
                    .host("geolite.info")
                    .build();

            InetAddress ipAddress = InetAddress.getByName(transaction.ipAddress());
            CityResponse response = client.city(ipAddress);

            City city = response.getCity();
            boolean ipAddressPotentialFraud = !Objects.equals(city.getName(), transaction.location().city());
            ipAddressDetails.add(ipAddressPotentialFraud ? "IP Address location differs from resident location" : "IP Address is not known to be fraudulent or malicious");

            return new Signal(Signal.SignalType.IP_ADDRESS, ipAddressPotentialFraud, ipAddressDetails);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Could not check IP address");
        }

    }
}
