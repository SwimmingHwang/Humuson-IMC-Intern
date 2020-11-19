package com.humuson.api.controller;

import com.google.gson.Gson;
import com.humuson.agent.domain.entity.AtMsgs;
import com.humuson.api.Producer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class AtController {

    @PostMapping(value = "/api/at-msg")
    @ResponseBody
    public String apiAtMsg(@RequestBody AtMsgs reqAt) {
        Gson gson = new Gson();
        String reqJsonData = gson.toJson(reqAt);
        System.out.println("API At Msg : "+reqJsonData);
        String stringStatusCode = Producer.produce(reqJsonData);
        System.out.println("IN AT CONTROLLER stringStatusCode : "+stringStatusCode);
        return stringStatusCode;//200 or 9000
    }

    @PostMapping(value = "/api/at-msgs")
    @ResponseBody
    public String apiAtMsgs(@RequestBody List<AtMsgs> reqAt) {
        Gson gson = new Gson();
        String stringStatusCode = "";
        for(AtMsgs msg:reqAt){
            String reqDataJson= gson.toJson(msg);
            System.out.println("API At Msgs : "+reqDataJson);
            stringStatusCode = Producer.produce(reqDataJson);
            System.out.println("IN AT CONTROLLER stringStatusCode : "+stringStatusCode);
        }
        return stringStatusCode;//200 or 9000
    }

    /*
    화면에 {message:"helloworld"} 라고 출력됩니다.
    */
    @GetMapping(value = "/helloworld/json")
    @ResponseBody
    public Hello helloworldJson() {
        Hello hello = new Hello();
        hello.message = "helloworld";
        return hello;
    }



    @Setter
    @Getter
    public static class Hello {
        private String message;
    }
}