package com.humuson.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.humuson.domain.entity.AtMsgsLog;
import com.humuson.dto.AtMsgsLogListDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Slf4j
@Service
public class ProducerService {

    @Value(value = "${kafka.at.log.topic.name}")
    private String TOPIC_NAME;

    @Value(value = "${kafka.bootstrap.address}")
    private String BOOTSTRAP_SERVERS;

    public void sendatMsgsLogList(List<AtMsgsLogListDto> atMsgsLogList) {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        Gson gson = new Gson();
        atMsgsLogList.stream().forEach((at) -> {
            String data = gson.toJson(at);
            ProducerRecord<String, String> record = new ProducerRecord(TOPIC_NAME, data);
            producer.send(record);
        });

        producer.flush();
        producer.close();
    }


}
