package com.humuson.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

@Slf4j
public class MyAcknowledgingMessageListener implements AcknowledgingMessageListener<String, Object> {
    @Override
    @KafkaListener(topics = "atMsgsLog", groupId = "at-group", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(ConsumerRecord<String, Object> data, Acknowledgment acknowledgment) {
        try {
            log.info("consume data: {}" , data.toString());
        } catch (Exception e) {
            log.info("consume cause exception : {}", e);
        }
    }
}
