package com.nicoardizzoli.customer.service;

import com.nicoardizzoli.amqp.RabbitMQMessageProducer;
import com.nicoardizzoli.clients.fraud.FraudCheckDto;
import com.nicoardizzoli.clients.fraud.FraudClient;
import com.nicoardizzoli.clients.notification.NotificationClient;
import com.nicoardizzoli.clients.notification.NotificationDto;
import com.nicoardizzoli.customer.dto.CustomerDto;
import com.nicoardizzoli.customer.model.Customer;
import com.nicoardizzoli.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate,
                              FraudClient fraudClient,
                              RabbitMQMessageProducer rabbitMQMessageProducer) {

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

        //CON SERVICE DISCOVERY USANDO EUREKA, CAMBIAMOS EL LOCALHOST:8082 por FRAUD que es el nombre del servicio en EUREKA.
        FraudCheckDto fraudCheckDto = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckDto.class, customerSaved.getId());
        if (fraudCheckDto.isFraudster()) {
            throw new IllegalAccessException("Fraudster");
        }

        //USANDO OPENFEIGN (suponiendo que el metodo de arriba lo repetimos en muchos microservicios
        Integer customerId = customerSaved.getId();
        if (!fraudClient.isFraudster(customerId).isFraudster()) {

            NotificationDto notificationDto = new NotificationDto(customerId, "NOTIFICACION ENVIADA AL CUSTOMER" + customerId);

            rabbitMQMessageProducer.publish(notificationDto, "internal.exchange", "internal.notification.routing-key");
            System.out.println("Notification sent");
        }


    }
}
