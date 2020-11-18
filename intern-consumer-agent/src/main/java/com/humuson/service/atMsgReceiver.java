package com.humuson.service;

import com.google.gson.Gson;
import com.humuson.dto.AtMsgsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class atMsgReceiver {

    private final AtMsgsService atMsgsService;

    @KafkaListener(topics = "mytopic")
    public void listen(@Payload String message) {
        log.info("message : {}", message);
        AtMsgsSaveRequestDto  atMsgstDto = new Gson().fromJson(message, AtMsgsSaveRequestDto.class);
        atMsgsService.save(atMsgstDto);
    }
}
