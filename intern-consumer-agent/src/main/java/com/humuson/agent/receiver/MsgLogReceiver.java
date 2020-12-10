package com.humuson.agent.receiver;

import com.google.gson.Gson;
import com.humuson.agent.domain.entity.AtMsgs;
import com.humuson.agent.domain.entity.MtMsgs;
import com.humuson.agent.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MsgLogReceiver {

    private final AtMsgsJdbcService atMsgsJdbcService;
    private final MtMsgsJdbcService mtMsgsJdbcService;


    @KafkaListener(topics = "${kafka.at.topic.name}", groupId = "${kafka.at.topic.group.name}")
    public void atLogListener(@Payload List<String> messages) {
        log.info("At Topic Listener : {}", messages);

        Gson gson = new Gson();
        AtMsgs atMsgsDto = null;
        List<AtMsgs> list = new ArrayList<>();

        for(String msg : messages) {
            log.info(msg);
            try {
                atMsgsDto = gson.fromJson(msg, AtMsgs.class);
                atMsgsDto.prePersist();
                list.add(atMsgsDto);
            } catch (Exception e) {
                log.info("it is not json format");
            }
        }
        if(!list.isEmpty())  atMsgsJdbcService.saveAll(list);
    }

    @KafkaListener(topics = "${kafka.mt.topic.name}", groupId = "${kafka.mt.topic.group.name}")
    public void mtLogListener(@Payload List<String> messages) {
        log.info("Mt Topic Listner : {}", messages);
        Gson gson = new Gson();
        MtMsgs mtMsgsDto = null;
        List<MtMsgs> list = new ArrayList<>();

        for(String msg : messages) {
            log.info(msg);
            try {
                mtMsgsDto = gson.fromJson(msg, MtMsgs.class);
                mtMsgsDto.prePersist();
                list.add(mtMsgsDto);
            } catch (Exception e) {
                log.info("it is not json format");
            }
        }
        if(!list.isEmpty())  mtMsgsJdbcService.saveAll(list);
    }



}
