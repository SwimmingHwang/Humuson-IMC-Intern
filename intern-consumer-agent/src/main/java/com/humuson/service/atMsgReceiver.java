package com.humuson.service;

import com.google.gson.Gson;
import com.humuson.agent.dto.AtMsgsSaveRequestDto;
import com.humuson.agent.dto.MtMsgsSaveRequestDto;
import com.humuson.agent.service.AtMsgsService;
import com.humuson.agent.service.MtMsgsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.store.FileLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class atMsgReceiver {

    private final AtMsgsService atMsgsService;
    private final MtMsgsService mtMsgsService;

    @Value("${kafka.at.topic.name}")
    private String AT_MSG_TOPIC;

    @KafkaListener(topics = "AT_MSG_TOPIC")
    public void atListen(@Payload String message) {
        log.info("At message : {}", message);
        AtMsgsSaveRequestDto atMsgstDto = new Gson().fromJson(message, AtMsgsSaveRequestDto.class);
        atMsgsService.save(atMsgstDto);
    }

    @Value("${kafka.mt.topic.name}")
    private String MT_MSG_TOPIC;

    @KafkaListener(topics = "MT_MSG_TOPIC")
    public void mtListen(@Payload String message) {
        log.info("Mt message : {}", message);
        MtMsgsSaveRequestDto mtMsgstDto = new Gson().fromJson(message, MtMsgsSaveRequestDto.class);
        mtMsgsService.save(mtMsgstDto);
    }

}
