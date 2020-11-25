package com.humuson;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
@PropertySource("classpath:kafka.properties")
//@RestController
@SpringBootApplication
public class KafkaApplication {


    private static String TOPIC_NAME = "atMsgsLog";
    private static String BOOTSTRAP_SERVERS = "localhost:9092";
    private static String GROUP_ID = "atMsgsLog-group";

    public static void main(String[] args) {
//        SpringApplication.run(KafkaApplication.class, args);
        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(configs);

        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        while (true) {
            ConsumerRecords<String, Object> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, Object> record : records) {
                String data = record.toString();

                log.info("Consume From " + TOPIC_NAME + " | data : " + data);
            }
        }

    }

}
