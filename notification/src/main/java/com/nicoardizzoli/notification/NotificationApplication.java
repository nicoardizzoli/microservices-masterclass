package com.nicoardizzoli.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {
        "com.nicoardizzoli.notification",
        "com.nicoardizzoli.amqp"
})
@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig notificationConfig){
//        return args -> producer.publish(new Person("nicko",14), notificationConfig.getInternalExchange(), notificationConfig.getInternalNotificationRoutingKey());
//    }
//
//
//    record Person(String name, Integer age){}

}
