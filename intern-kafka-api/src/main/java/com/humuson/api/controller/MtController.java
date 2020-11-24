package com.humuson.api.controller;

import com.google.gson.Gson;
import com.humuson.agent.dto.MtMsgsSaveRequestDto;
import com.humuson.api.Producer;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@Tag(name="문자톡 API")
@Slf4j
@Controller
public class MtController {

//    @Operation(summary = "문자톡 Kafka로 전송")
    @PostMapping(value = "/api/mt-msg",produces = "application/json; charset=utf8")
    @ResponseBody
    public String apiMtMsg(@RequestBody MtMsgsSaveRequestDto requestDto) {
        log.info(requestDto.getMsg());
        Gson gson = new Gson();
        String reqJsonData = gson.toJson(requestDto);
        log.info("API Mt Msg : " + reqJsonData);
        String stringStatusCode = Producer.produce(reqJsonData,1);
        log.info("IN AT CONTROLLER stringStatusCode : " + stringStatusCode);
        return stringStatusCode;//200 or 9000
    }

//    @Operation(summary = "문자톡 리스트 Kafka로 전송")
    @PostMapping(value = "/api/mt-msgs",produces = "application/json; charset=utf8")
    @ResponseBody
    public String apiMtMsgs(@RequestBody List<MtMsgsSaveRequestDto> requestDto) {
        log.info(requestDto.toString());
        Gson gson = new Gson();
        String stringStatusCode = "";
        for (MtMsgsSaveRequestDto msg : requestDto) {
            String reqDataJson = gson.toJson(msg);
            log.info("API Mt Msgs : " + reqDataJson);
            stringStatusCode = Producer.produce(reqDataJson,1);
            log.info("IN AT CONTROLLER stringStatusCode : " + stringStatusCode);
        }
        return stringStatusCode;//200 or 9000
    }
}