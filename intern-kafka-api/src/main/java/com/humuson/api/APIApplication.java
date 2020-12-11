package com.humuson.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@PropertySource("classpath:kafka.properties")
@EnableScheduling
@SpringBootApplication
public class APIApplication {
    public static void main(String[] args) {
        SpringApplication.run(APIApplication.class, args);
    }
}