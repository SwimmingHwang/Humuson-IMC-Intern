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

//    @Bean
//    public KafkaHealthProperties kafkaHealthProperties() {
//        return new KafkaHealthProperties();
//    }
//
//    @Bean
//    public KafkaConsumingHealthIndicator kafkaConsumingHealthIndicator(KafkaHealthProperties kafkaProperties,
//                                                                       KafkaProperties processingProperties) {
//        return new KafkaConsumingHealthIndicator(kafkaProperties, processingProperties.buildConsumerProperties(),
//                processingProperties.buildProducerProperties());
//    }
}
