package com.humuson.acutator;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Slf4j
@Getter
@Setter
@Component
public class KafkaHealthIndicator implements HealthIndicator {

    private String kafkaHealth;

    @Override
    public Health health() {
        try {
            String status = getKafkaHealth();
            if(status.equals("200")) {
                setKafkaHealth("");
                log.info("200");
                return Health
                        .up()
                        .withDetail("status", "Kafka Server up")
                        .build();
            } else {
                log.info("900");
                return Health
                        .down()
                        .withDetail("status", "Kafka Server Down")
                        .build();
            }
        } catch (Exception e) {
            log.info("9999");
            return Health
                    .up()
                    .withDetail("status", "Error : " + e.toString())
                    .build();
        }
    }
}

