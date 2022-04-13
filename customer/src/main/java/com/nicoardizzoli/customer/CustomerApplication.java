package com.nicoardizzoli.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        //esto es para poder inyectar las clases q esten en esos packages
        scanBasePackages = {
                "com.nicoardizzoli.amqp",
                "com.nicoardizzoli.customer"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.nicoardizzoli.clients"
)
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
