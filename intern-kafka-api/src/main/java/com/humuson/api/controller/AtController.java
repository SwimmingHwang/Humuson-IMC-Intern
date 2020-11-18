package com.humuson.api.controller;

import com.google.gson.Gson;
import com.humuson.api.Producer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AtController {
    /*
    1. 화면에 helloworld가 출력됩니다.
    */
//    @GetMapping(value = "/helloworld/string")
//    @ResponseBody
//    public String helloworldString() {
//        return "helloworld";
//    }

    @PostMapping(value = "/helloworld/string")
    @ResponseBody
    public String helloworldString(@RequestBody At reqat) {
        Gson gson = new Gson();
        String res = gson.toJson(reqat);
        System.out.println("res"+res);
        Producer.produce(res);
//        At at = new At(reqat.getMsg(),reqat.getPhoneNumber(), reqat.getReservedDate(),reqat.getTemplateCode());
        return res;
    }


    /*
    2. 화면에 {message:"helloworld"} 라고 출력됩니다.
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
    public static class At{
        private String msg;
        private String phoneNumber;
        private String templateCode;
        private String reservedDate;

        public At(String msg, String phoneNumber, String templateCode, String reservedDate) {
            this.msg = msg;
            this.phoneNumber = phoneNumber;
            this.templateCode = templateCode;
            this.reservedDate = reservedDate;
        }
    }


    @Setter
    @Getter
    public static class Hello {
        private String message;
    }
}