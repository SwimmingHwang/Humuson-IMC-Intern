package com.humuson;

import com.deviceinsight.kafka.health.KafkaConsumingHealthIndicator;
import com.deviceinsight.kafka.health.KafkaHealthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaHealthCheckApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaHealthCheckApplication.class, args);
    }

    @Bean
    @ConfigurationProperties("kafka.health")
    public KafkaHealthProperties kafkaHealthProperties() {
        return new KafkaHealthProperties();
    }

    @Bean
    public KafkaConsumingHealthIndicator kafkaConsumingHealthIndicator(KafkaHealthProperties kafkaProperties,
                                                                       KafkaProperties processingProperties) {
        return new KafkaConsumingHealthIndicator(kafkaHealthProperties(), processingProperties.buildConsumerProperties(),
                processingProperties.buildProducerProperties());
    }
}