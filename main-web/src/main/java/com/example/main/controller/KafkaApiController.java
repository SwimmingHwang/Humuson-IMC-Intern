package com.example.main.controller;

import com.example.kafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KafkaApiController {

    @Autowired
    KafkaService kafkaService;

    @RequestMapping("/kafka")
    public String index(){

        System.out.println(kafkaService.test());
        return "index";
    }
}
