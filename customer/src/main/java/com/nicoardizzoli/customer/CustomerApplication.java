package com.nicoardizzoli.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        //esto es para poder inyectar las clases q esten en esos packages
        scanBasePackages = {
                "com.nicoardizzoli.amqp",
                "com.nicoardizzoli.customer"
        }
)
//@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.nicoardizzoli.clients"
)

//esto es para leer las properties de clients
@PropertySources(
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
)
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
