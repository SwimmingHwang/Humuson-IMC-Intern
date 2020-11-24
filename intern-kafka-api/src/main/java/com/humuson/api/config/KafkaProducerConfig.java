package com.humuson.api.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableKafka
@Slf4j
public class KafkaProducerConfig {

    private static final Logger logger = LoggerFactory.getLogger(com.humuson.api.Producer.class);

    private static String AT_TOPIC_NAME;
    private static String MT_TOPIC_NAME;
    private static String BOOTSTRAP_SERVERS;

    @Value("${kafka.bootstrap.address}")
    public void setBootstrapServers(String address) {
        BOOTSTRAP_SERVERS = address;
    }
    @Value("${kafka.at.topic.name}")
    public void setTopicAtName(String topicName){
        AT_TOPIC_NAME = topicName;
    }
    @Value("${kafka.mt.topic.name}")
    public void setTopicMtName(String topicName){
        MT_TOPIC_NAME = topicName;
    }

    @Bean
    public ProducerFactory<String, byte[]> producerFactory() {
        return new DefaultKafkaProducerFactory<>(senderProps());
    }

    public Map<String, Object> senderProps() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 10000);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 200);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 22 * 1024 * 1024); // 22 Mbyte
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, SimplePartitional.class);
        return props;
    }

    /**
     * kafka partitioning 전략
     */
    public static class SimplePartitional extends DefaultPartitioner {

        private int num;

        @Override
        public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
            List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
            return (num++ + 1) % partitions.size();
        }
    }
    @Bean
    public KafkaTemplate<String, byte[]> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}

