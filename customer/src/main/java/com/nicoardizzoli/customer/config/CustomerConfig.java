package com.nicoardizzoli.customer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {

    @Bean
    @LoadBalanced //esto es para cuando hay muchas instancias de un servicio, vaya a uno.
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
