package com.humuson.api.controller;

import com.google.gson.Gson;
import com.humuson.agent.dto.AtMsgsSaveRequestDto;
import com.humuson.agent.dto.MtMsgsSaveRequestDto;

import com.humuson.api.Producer;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
//@Tag(name="알림톡 API")
@Slf4j
@Controller
public class AtController {

//    @Operation(summary = "알림톡 Kafka로 전송")
    @PostMapping(value = "/api/at-msg",produces = "application/json; charset=utf8")
    @ResponseBody
    public String apiAtMsg(@RequestBody AtMsgsSaveRequestDto requestDto) {
        log.info(requestDto.getMsg());
        Gson gson = new Gson();
        String reqJsonData = gson.toJson(requestDto);
        log.info("API At Msg : " + reqJsonData);
        String stringStatusCode = Producer.produce(reqJsonData,0);
        log.info("IN AT CONTROLLER stringStatusCode : " + stringStatusCode);
        return stringStatusCode;//200 or 9000
    }
//    @Operation(summary = "문자톡 리스트 Kafka로 전송")
//    @PostMapping(value = "/api/at-msgs",produces = "application/json; charset=utf8")
//    @ResponseBody
//    public String apiAtMsgs(@RequestBody List<AtMsgsSaveRequestDto> requestDto) {
//        log.info(requestDto.toString());
//
//        String stringStatusCode = "";
//        Gson gson = new Gson();
//        for (AtMsgsSaveRequestDto msg : requestDto) {
//            String reqDataJson = gson.toJson(msg);
//            log.info("API At Msgs : " + reqDataJson);
//            stringStatusCode = Producer.produce(reqDataJson,0);
//            log.info("IN AT CONTROLLER stringStatusCode : " + stringStatusCode);
//        }
//
//        return stringStatusCode;//200 or 9000
//    }

//    @Operation(summary = "문자톡 리스트 Kafka로 전송")
    @PostMapping(value = "/api/at-msgs",produces = "application/json; charset=utf8")
    @ResponseBody
    public String apiAtMsgs(@RequestBody List<AtMsgsSaveRequestDto> requestDto) {
//        log.info(requestDto.toString());

//        Gson gson = new Gson();
//        for (AtMsgsSaveRequestDto msg : requestDto) {
//            String reqDataJson = gson.toJson(msg);
//            log.info("API At Msgs : " + reqDataJson);
//            stringStatusCode = Producer.produce(reqDataJson,0);
//            log.info("IN AT CONTROLLER stringStatusCode : " + stringStatusCode);
//        }
        String stringStatusCode = "";
        stringStatusCode = Producer.batchProduce(requestDto,0);

        return stringStatusCode;//200 or 9000
    }
}