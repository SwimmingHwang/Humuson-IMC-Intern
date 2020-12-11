package com.humuson.actuator;

import com.humuson.config.KafkaProducerConfig;
import com.humuson.utility.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaHealthIndicator implements HealthIndicator {

//    private final Producer producer;

    @Value(value = "${kafka.bootstrap.address}")
    private String BOOTSTRAP_SERVERS;

    @Value(value = "${kafka.at.log.topic.name}")
    private String AT_REPORT_TOPIC_NAME;

    @Value(value = "${kafka.mt.log.topic.name}")
    private String MT_REPORT_TOPIC_NAME;

    @Value(value = "${kafka.health.check.topic.name}")
    private String HC_TOPIC_NAME;

    private final KafkaProducerConfig kafkaProducerConfig;

    public String kafkaHealthCheckProduce() {
        try {
            Properties configs = new Properties();
            configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
            configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

            KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

            ProducerRecord<String, String> record = new ProducerRecord<>(HC_TOPIC_NAME, "1");
            try {
                producer.send(record);
            } catch (Exception e) {
                return "9000";
            }

            producer.flush();
            producer.close();
            return "200";
        } catch (Exception e) {
            return "9999";
        }
    }

    @Override
    public Health health() {
        try {
            String status = kafkaHealthCheckProduce();
            if(status.equals("200")) {
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

