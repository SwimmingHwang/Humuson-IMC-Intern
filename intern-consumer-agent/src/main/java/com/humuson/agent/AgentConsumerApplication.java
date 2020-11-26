package com.humuson.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:kafka.properties")
@SpringBootApplication
public class AgentConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgentConsumerApplication.class, args);
    }
}
