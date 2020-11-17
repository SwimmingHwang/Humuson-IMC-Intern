package com.humuson.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootStrapAddress;

    @Value(value = "${retries}")
    private String retries;

    @Value(value = "${batch.size}")
    private String batchSize;

    @Value(value = "${linger.ms}")
    private String lingerMs;

    @Value(value = "${buffer.memory}")
    private String bufferMemory;

    public Map<String, Object> producerConfigs() {

        Map<String, Object> props = new HashMap<>();

        // server host 및 port 지정
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapAddress);

        // retries 횟수
        props.put(ProducerConfig.RETRIES_CONFIG, retries);

        // batch size 지정
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);

        // linger.ms
        props.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);

        // buffer memory size 지정
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);

        // key serialize 지정
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // value serialize 지정
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return props;
    }


    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        // Bean을 통하여 의존성 주입
        return new KafkaTemplate<>(producerFactory());
    }

}
