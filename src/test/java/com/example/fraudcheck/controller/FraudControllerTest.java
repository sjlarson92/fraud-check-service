package com.example.fraudcheck.controller;

import com.example.fraudcheck.model.*;
import com.example.fraudcheck.service.IPAddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
class FraudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IPAddressService ipAddressService;

    @InjectMocks
    private FraudController fraudController;

    @BeforeEach
    void setUp() {
        Signal ipAddressSignal = new Signal(Signal.SignalType.IP_ADDRESS, false, new ArrayList<>());

        Mockito.when(ipAddressService.getIPAddressSignal(Mockito.any()))
                .thenReturn(ipAddressSignal);
    }

    @Nested
    class CreateFraudScore {
        @Test
        void createFraudScore() throws Exception {
            Location location = new Location("Springfield", "MO");
            Transaction requestBody = new Transaction(
                    "Waldo",
                    "123.456.789",
                    location,
                    new PaymentDetails(1234, "Waldo", BigDecimal.valueOf(22.00)),
                    new TransactionDetails("Merchant Name", location, 2)

            );

            mockMvc.perform(MockMvcRequestBuilders.post("/api/score-transaction")
                            .content(objectMapper.writeValueAsString(requestBody))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(result -> { FraudScore responseBody = objectMapper.readValue(result.getResponse().getContentAsString(), FraudScore.class);
                        assertThat(responseBody.signals()).hasSize(4);
                    });

        }

        @Test
        void whenInvalidCustomerNameThrowsException() throws Exception {

            Location location = new Location("Springfield", "MO");

            Transaction requestBody = new Transaction(
                    "",
                    "123.456.789",
                    location,
                    new PaymentDetails(1234, "Waldo", BigDecimal.valueOf(22.00)),
                    new TransactionDetails("Merchant Name", location, 2)

            );

            mockMvc.perform(MockMvcRequestBuilders.post("/api/score-transaction")
                            .content(objectMapper.writeValueAsString(requestBody))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

        }
    }


}