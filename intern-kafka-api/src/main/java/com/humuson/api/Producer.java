package com.humuson.api;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private static String tmp;

    @Value("${kafka.bootstrap.address}")
    public void setTemp(String path) {
        tmp = path;
    }

    private static String TOPIC_NAME = "AT_MSG_TOPIC";
    private static String BOOTSTRAP_SERVERS = tmp;

    public static String produce(String args) {
        // TODO : String[] stream으로 처리할 것 .
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        String stringStatusCode = "";
//        for (int index = 0; index < args.length; index++) {
        try {
            String data = args + "";
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, data);

            producer.send(record);

            logger.info("Send to " + TOPIC_NAME + " | data : " + data);
            stringStatusCode = "200";
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            stringStatusCode = "9000";
        }
//        }

        producer.flush();
        producer.close();
        return stringStatusCode;
    }
}