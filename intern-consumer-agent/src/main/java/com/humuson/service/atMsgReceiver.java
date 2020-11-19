package com.humuson.service;

import com.google.gson.Gson;
import com.humuson.agent.dto.AtMsgsSaveRequestDto;
import com.humuson.agent.service.AtMsgsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class atMsgReceiver {

    private final AtMsgsService atMsgsService;

    @Value("${kafka.at.topic.name}")
    private String AT_MSG_TOPIC;

    @KafkaListener(topics = "AT_MSG_TOPIC")
    public void listen(@Payload String message) {
        log.info("message : {}", message);
        AtMsgsSaveRequestDto atMsgstDto = new Gson().fromJson(message, AtMsgsSaveRequestDto.class);
        atMsgsService.save(atMsgstDto);
    }

}
