package com.humuson.controller;

import com.humuson.utility.ApiCall;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@RestController
public class TokenController {

    @GetMapping("/api/v1/token")
    @ResponseBody
    public String setToken() throws IOException {

        Document doc = Jsoup.connect("http://localhost:8080/home").get();
        Elements contents = doc.select("#page-top > div:nth-child(1) > meta:nth-child(6)");
        String stringTokenTag  = contents.toString();

        //리얼 토큰으로 바꾸기
        String token = stringTokenTag.substring(28,stringTokenTag.length()-2);
        log.info("Token Controller : " +token);
        return token;
    }
}
