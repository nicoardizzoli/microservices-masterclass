package com.nicoardizzoli.customer.service;

import com.nicoardizzoli.customer.dto.CustomerDto;
import com.nicoardizzoli.customer.model.Customer;
import com.nicoardizzoli.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void registerCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .lastName(customerDto.lastName())
                .firstName(customerDto.firstName())
                .email(customerDto.email())
                .build();

        //TODO: check if email valid
        //TODO: check if email not taken
        customerRepository.save(customer);


    }
}
