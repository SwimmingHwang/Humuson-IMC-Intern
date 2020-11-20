package com.humuson.controller;

import com.google.gson.Gson;
import com.humuson.call.ApiCall;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.dto.at.*;
import com.humuson.service.AtMsgsService;
import com.humuson.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AtMsgsApiController {

    private final AtMsgsService atMsgsService;
    private final CustomerService customerService;

    @PostMapping("/api/v1/at-msgs")
    public String save(@RequestBody AtMsgsSaveRequestDto requestDto) {
        Gson gson = new Gson();
        String reqData = gson.toJson(requestDto);
        log.info("Request Data : " + reqData);
        String statusCode = ApiCall.post("http://localhost:8082/api/at-msg",reqData);
        log.info("statusCode :"+statusCode);
        if (statusCode.equals("200")){
            atMsgsService.save(requestDto);
        }
        return statusCode;
    }

    @PostMapping(value = "/api/v1/multi-at-msgs",produces = "application/json; charset=utf8")
    public String saveAll(@RequestBody MultiAtMsgsSaveRequestDto requestDto) {
        Gson gson = new Gson();
        List<AtMsgs> atMsgs = requestDto.toEntity();
        String reqData = gson.toJson(atMsgs);
        log.info("Request Data : " +reqData);
        String statusCode = ApiCall.post("http://localhost:8082/api/at-msgs",reqData);
        log.info("statusCode :"+statusCode);

        if (statusCode.equals("200")){
            atMsgsService.saveAll(requestDto);
        }
        return statusCode;
    }
    @PostMapping("/api/v1/multi-at-msgs/list")
    public String saveAllList(@RequestBody MultiAtMsgsSaveListRequestDto requestDto) {
        Gson gson = new Gson();
        List<AtMsgs> atMsgs = requestDto.toEntity(customerService.findAll());
        String reqData = gson.toJson(atMsgs);
        log.info("Request Data : " +reqData);
        String statusCode = ApiCall.post("http://localhost:8082/api/at-msgs",reqData);
        log.info("statusCode :"+statusCode);

        if (statusCode.equals("200")){
            atMsgsService.saveAllList(requestDto);
        }
        return statusCode;
    }
    @PutMapping("/api/v1/at-msgs/{id}")
    public Integer update(@PathVariable Integer id, @RequestBody AtMsgsUpdateRequestDto requestDto) {
        return atMsgsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/at-msgs/{id}")
    public Integer delete(@PathVariable Integer id) {
        atMsgsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/at-msgs/{id}")
    public AtMsgsResponseDto findById(@PathVariable Integer id) {
        return atMsgsService.findById(id);
    }

    @GetMapping("/api/v1/at-msgs/list")
    public List<AtMsgsListResponseDto> findAll() {
        return atMsgsService.findAllDesc();
    }
}