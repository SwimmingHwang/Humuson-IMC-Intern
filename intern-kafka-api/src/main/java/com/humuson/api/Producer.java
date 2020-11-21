package com.humuson.api;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

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

    public static String produce(String args, int topicIdx) {
        // TODO : String[] stream으로 처리할 것 .
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        String topicName = "";
        if (topicIdx==0){
            topicName = AT_TOPIC_NAME;
        }
        else if (topicIdx == 1){
            topicName = MT_TOPIC_NAME;
        }

        String stringStatusCode = "";
//        for (int index = 0; index < args.length; index++) {
        try {
            String data = args + "";
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, data);

            producer.send(record);

            logger.info("Send to " + topicName + " | data : " + data);
            stringStatusCode = "200";
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            stringStatusCode = "9000";
        } finally{
            producer.flush();
            producer.close();
        }

        return stringStatusCode;
    }
}