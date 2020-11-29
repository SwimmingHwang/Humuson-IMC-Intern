package com.humuson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.annotation.EnableRetry;


@PropertySource("classpath:kafka.properties")
@SpringBootApplication
public class ClientConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientConsumerApplication.class, args);
    }
}
