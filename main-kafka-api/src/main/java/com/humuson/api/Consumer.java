package com.humuson.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;


import com.humuson.api.AtMsgsService;

public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private static String TOPIC_NAME = "AT_MSG_TOPIC";
    private static String BOOTSTRAP_SERVERS = "localhost:9092";
    private static String GROUP_ID = "test-group";


    public static void main(String[] args) throws IOException {
        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);

        consumer.subscribe(Arrays.asList(TOPIC_NAME));



        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                // TODO : json string이 아니거나

                JsonObject jsonObject = JsonParser.parseString(record.value()).getAsJsonObject();
                System.out.println(jsonObject.get("msg"));


//                ObjectMapper mapper = new ObjectMapper();


//                At at = mapper.readValue(record.value(),At.class);

//                System.out.println(at.getMsg()+at.getPhoneNumber()+"");
                System.out.println("\n"+record.value());

                String data = record.toString();
                logger.info("Consume From " + TOPIC_NAME + " | data : " + data);
            }
        }
    }
}