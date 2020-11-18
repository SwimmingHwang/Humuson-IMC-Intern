package com.example.main.controller;

import com.example.main.call.ApiCall;
import com.example.main.domain.msgs.AtMsgs;
import com.example.main.dto.*;
import com.example.main.service.AtMsgsService;
import com.example.main.service.CustomerService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AtMsgsApiController {

    private final AtMsgsService atMsgsService;
    private final CustomerService customerService;

    @PostMapping("/api/v1/at-msgs")
    public String save(@RequestBody AtMsgsSaveRequestDto requestDto) {
        Gson gson = new Gson();
        String reqData = gson.toJson(requestDto);
        System.out.println("Request DTA : "+reqData);

        String statusCode = ApiCall.post("http://localhost:8082/api/at-msg",reqData);
        if (statusCode=="200"){
            atMsgsService.save(requestDto);
        }
        return statusCode;
    }

    @PostMapping("/api/v1/multi-at-msgs")
    public String saveAll(@RequestBody MultiAtMsgsSaveRequestDto requestDto) {
        Gson gson = new Gson();
        List<AtMsgs> atMsgs = requestDto.toEntity();
        String reqData = gson.toJson(atMsgs);
        System.out.println("Request Data : "+reqData);

        String statusCode = ApiCall.post("http://localhost:8082/api/at-msgs",reqData);
        if (statusCode=="200"){
            atMsgsService.saveAll(requestDto);
        }
        return statusCode;
//        return atMsgsService.saveAll(requestDto);
    }
    @PostMapping("/api/v1/multi-at-msgs/list")
    public List<AtMsgs> saveAllList(@RequestBody MultiAtMsgsSaveListRequestDto requestDto) {
        return atMsgsService.saveAllList(requestDto);
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