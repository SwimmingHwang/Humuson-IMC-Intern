package com.humuson.agent;

import com.deviceinsight.kafka.health.KafkaConsumingHealthIndicator;
import com.deviceinsight.kafka.health.KafkaHealthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:kafka.properties")
@SpringBootApplication
public class AgentConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgentConsumerApplication.class, args);
    }

    @Bean
    public KafkaHealthProperties kafkaHealthProperties() {
        return new KafkaHealthProperties();
    }

    @Bean
    public KafkaConsumingHealthIndicator kafkaConsumingHealthIndicator(KafkaHealthProperties kafkaProperties,
                                                                       KafkaProperties processingProperties) {
        return new KafkaConsumingHealthIndicator(kafkaProperties, processingProperties.buildConsumerProperties(),
                processingProperties.buildProducerProperties());
    }

}
