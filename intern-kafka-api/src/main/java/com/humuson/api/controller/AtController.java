package com.humuson.api.controller;

import com.google.gson.Gson;
import com.humuson.agent.domain.entity.AtMsgs;
import com.humuson.api.Producer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Controller
public class AtController {

    @PostMapping(value = "/api/at-msg")
    @ResponseBody
    public String apiAtMsg(@RequestBody AtMsgs reqAt) {
        Gson gson = new Gson();
        String reqJsonData = gson.toJson(reqAt);
        log.info("API At Msg : " + reqJsonData);
        String stringStatusCode = Producer.produce(reqJsonData);
        log.info("IN AT CONTROLLER stringStatusCode : " + stringStatusCode);
        return stringStatusCode;//200 or 9000
    }

    @PostMapping(value = "/api/at-msgs")
    @ResponseBody
    public String apiAtMsgs(@RequestBody List<AtMsgs> reqAt) {
        Gson gson = new Gson();
        String stringStatusCode = "";
        for (AtMsgs msg : reqAt) {
            String reqDataJson = gson.toJson(msg);
            log.debug("API At Msgs : " + reqDataJson);
            stringStatusCode = Producer.produce(reqDataJson);
            log.debug("IN AT CONTROLLER stringStatusCode : " + stringStatusCode);
        }
        return stringStatusCode;//200 or 9000
    }
}