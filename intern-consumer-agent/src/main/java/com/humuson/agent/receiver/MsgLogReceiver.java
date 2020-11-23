package com.humuson.agent.receiver;

import com.google.gson.Gson;
import com.humuson.agent.dto.AtMsgsSaveRequestDto;
import com.humuson.agent.dto.FtMsgsSaveRequestDto;
import com.humuson.agent.dto.MtMsgsSaveRequestDto;
import com.humuson.agent.service.AtMsgsService;
import com.humuson.agent.service.FtMsgsService;
import com.humuson.agent.service.MtMsgsService;
import com.humuson.agent.utiliy.ApiCall;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@RequiredArgsConstructor
@Service
public class MsgLogReceiver {

    private final AtMsgsService atMsgsService;
    private final FtMsgsService ftMsgsService;
    private final MtMsgsService mtMsgsService;

    @KafkaListener(topics = "${kafka.at.topic.name}", groupId = "${kafka.at.topic.group.name}")
    public void atLoglistenr(@Payload String message) throws IOException {
        log.info("At Topic Listner : {}", message);
        Gson gson = new Gson();
        AtMsgsSaveRequestDto atMsgstDto = null;
        try {
            atMsgstDto = gson.fromJson(message, AtMsgsSaveRequestDto.class);
        } catch (Exception e) {
            log.info("it is not json format");
        }
        if(atMsgstDto != null) {
            atMsgsService.save(atMsgstDto); // agent DB에 atMsgs 저장
//            atMsgstDto.setStatus();
//            String status = ApiCall.put("http://localhost:8080/api/v1/at-msgs/" + atMsgstDto.getId().toString(), "3"); // client DB에 status 바꾸는 api 호출
//            log.info("at msg status change : {}", status);
        }
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
