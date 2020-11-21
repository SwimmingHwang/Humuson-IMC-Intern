package com.humuson.controller;

import com.google.gson.Gson;
import com.humuson.call.ApiCall;
import com.humuson.domain.msgs.MtMsgs;
import com.humuson.dto.mt.*;
import com.humuson.service.CustomerService;
import com.humuson.service.MtMsgsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MtMsgsApiController {

    private final MtMsgsService mtMsgsService;
    private final CustomerService customerService;

    @PostMapping("/api/v1/mt-msgs")
    public String save(@RequestBody MtMsgsSaveRequestDto requestDto) {
        Gson gson = new Gson();
        String reqData = gson.toJson(requestDto);
        log.info("Request Data : " + reqData);
        String statusCode = ApiCall.post("http://localhost:8082/api/mt-msg",reqData);
        log.info("statusCode :"+statusCode);
        if (statusCode.equals("200")){
            mtMsgsService.save(requestDto);
        }
        return statusCode;
    }
    @PostMapping(value = "/api/v1/multi-mt-msgs", produces = "application/json; charset=utf8")
    public String saveAll(@RequestBody MultiMtMsgsSaveRequestDto requestDto) {
        Gson gson = new Gson();
        List<MtMsgs> mtMsgs = requestDto.toEntity();
        String reqData = gson.toJson(mtMsgs);
        log.info("Request Data : " +reqData);
        String statusCode = ApiCall.post("http://localhost:8082/api/mt-msgs",reqData);
        log.info("statusCode :"+statusCode);

        if (statusCode.equals("200")){
            mtMsgsService.saveAll(requestDto);
        }
        return statusCode;
    }

    @PostMapping("/api/v1/multi-mt-msgs/list")
    public String saveAllList(@RequestBody MultiMtMsgsSaveListRequestDto requestDto) {
        Gson gson = new Gson();
        List<MtMsgs> mtMsgs = requestDto.toEntity(customerService.findAll());
        String reqData = gson.toJson(mtMsgs);
        log.info("Request Data : " +reqData);
        String statusCode = ApiCall.post("http://localhost:8082/api/mt-msgs",reqData);
        log.info("statusCode :"+statusCode);

        if (statusCode.equals("200")){
            mtMsgsService.saveAllList(requestDto);
        }
        return statusCode;
    }

    @PutMapping("/api/v1/mt-msgs/{id}")
    public Integer update(@PathVariable Integer id, @RequestBody MtMsgsUpdateRequestDto requestDto) {
        return mtMsgsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/mt-msgs/{id}")
    public Integer delete(@PathVariable Integer id) {
        mtMsgsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/mt-msgs/{id}")
    public MtMsgsResponseDto findById(@PathVariable Integer id) {
        return mtMsgsService.findById(id);
    }

    @GetMapping("/api/v1/mt-msgs/list")
    public List<MtMsgsListResponseDto> findAll() {
        return mtMsgsService.findAllDesc();
    }
}