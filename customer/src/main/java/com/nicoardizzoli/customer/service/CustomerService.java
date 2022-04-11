package com.nicoardizzoli.customer.service;

import com.nicoardizzoli.customer.dto.CustomerDto;
import com.nicoardizzoli.customer.dto.FraudCheckDto;
import com.nicoardizzoli.customer.model.Customer;
import com.nicoardizzoli.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

    public void registerCustomer(CustomerDto customerDto) throws IllegalAccessException {
        Customer customer = Customer.builder()
                .lastName(customerDto.lastName())
                .firstName(customerDto.firstName())
                .email(customerDto.email())
                .build();

        //TODO: check if email valid
        //TODO: check if email not taken
        Customer customerSaved = customerRepository.saveAndFlush(customer);
        //TODO: check if fraudster
        FraudCheckDto fraudCheckDto = restTemplate.getForObject("http://localhost:8082/api/v1/fraud-check/{customerId}", FraudCheckDto.class, customerSaved.getId());
        if (fraudCheckDto.isFraudster()) {
            throw new IllegalAccessException("Fraudster");
        }
        //TODO: send notification


    }
}
