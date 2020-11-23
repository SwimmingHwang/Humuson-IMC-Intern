package com.humuson.agent.receiver;

import com.google.gson.Gson;
import com.humuson.agent.domain.entity.AtMsgs;
import com.humuson.agent.dto.AtMsgsSaveRequestDto;
import com.humuson.agent.dto.FtMsgsSaveRequestDto;
import com.humuson.agent.dto.MtMsgsSaveRequestDto;
import com.humuson.agent.service.AtMsgsJdbcService;
import com.humuson.agent.service.AtMsgsService;
import com.humuson.agent.service.FtMsgsService;
import com.humuson.agent.service.MtMsgsService;
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

    private final AtMsgsService atMsgsService;
    private final AtMsgsJdbcService atMsgsJdbcService;
    private final FtMsgsService ftMsgsService;
    private final MtMsgsService mtMsgsService;

    @KafkaListener(topics = "${kafka.at.topic.name}", groupId = "${kafka.at.topic.group.name}")
    public void atLoglistenr(@Payload List<String> messages) {
        log.info("At Topic Listner : {}", messages);
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

    @KafkaListener(topics = "${kafka.ft.topic.name}", groupId = "${kafka.ft.topic.group.name}")
    public void ftLoglistenr(@Payload String message) {
        log.info("Ft Topic Listner : {}", message);
        Gson gson = new Gson();
        FtMsgsSaveRequestDto ftMsgstDto = null;
        try {
            ftMsgstDto = new Gson().fromJson(message, FtMsgsSaveRequestDto.class);
        } catch (Exception e) {
            log.info("it is not json format");
        }
        if(ftMsgstDto != null) ftMsgsService.save(ftMsgstDto);
    }

    @KafkaListener(topics = "${kafka.mt.topic.name}", groupId = "${kafka.mt.topic.group.name}")
    public void mtLoglistenr(@Payload String message) {
        Gson gson = new Gson();
        MtMsgsSaveRequestDto mtMsgstDto = null;
        log.info("Mt Topic Listner : {}", message);
        try {
            mtMsgstDto = new Gson().fromJson(message, MtMsgsSaveRequestDto.class);
        } catch (Exception e) {
            log.info("it is not json format");
        }
        if(mtMsgstDto != null) mtMsgsService.save(mtMsgstDto);
    }

}
