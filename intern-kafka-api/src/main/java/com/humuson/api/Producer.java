package com.humuson.api;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private static String TOPIC_NAME = "mytopic";
    private static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void produce(String args) {
        // TODO : String[] stream으로 처리할 것 .
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

//        for (int index = 0; index < args.length; index++) {
            try {
                String data = args + "";
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, data);

                producer.send(record);

                logger.info("Send to " + TOPIC_NAME + " | data : " + data);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
//        }

        producer.flush();
        producer.close();
    }
}