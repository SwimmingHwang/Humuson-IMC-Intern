package com.humuson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@PropertySource("classpath:kafka.properties")
@SpringBootApplication
public class ClientConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientConsumerApplication.class, args);
    }
}
