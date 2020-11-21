package com.humuson.api.controller;

import com.google.gson.Gson;
import com.humuson.agent.dto.AtMsgsSaveRequestDto;
import com.humuson.agent.dto.MtMsgsSaveRequestDto;
import com.humuson.api.Producer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Controller
public class AtController {

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

    @PostMapping(value = "/api/at-msgs",produces = "application/json; charset=utf8")
    @ResponseBody
    public String apiAtMsgs(@RequestBody List<AtMsgsSaveRequestDto> requestDto) {
        log.info(requestDto.toString());
        Gson gson = new Gson();
        String stringStatusCode = "";
        for (AtMsgsSaveRequestDto msg : requestDto) {
            String reqDataJson = gson.toJson(msg);
            log.info("API At Msgs : " + reqDataJson);
            stringStatusCode = Producer.produce(reqDataJson,0);
            log.info("IN AT CONTROLLER stringStatusCode : " + stringStatusCode);
        }
        return stringStatusCode;//200 or 9000
    }
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