package com.nicoardizzoli.fraud.controller;

import com.nicoardizzoli.fraud.service.FraudCheckService;
import dto.FraudCheckDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("{customerId}")
    public FraudCheckDto isFraudster(@PathVariable("customerId") Integer customerId){
        boolean fraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);
        return new FraudCheckDto(fraudulentCustomer);
    }
}
