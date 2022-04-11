package com.nicoardizzoli.fraud.service;

import com.nicoardizzoli.fraud.model.FraudCheckHistory;
import com.nicoardizzoli.fraud.repository.FraudCheckHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        //es unicamente para ver como comunicar microservicios, este servicio no va a tener funcionalidad de chequear el fraud.

        FraudCheckHistory fraudCheck = FraudCheckHistory.builder()
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build();

        fraudCheckHistoryRepository.save(fraudCheck);

        return false;
    }
}
