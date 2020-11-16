package com.example.main.controller;

import com.example.main.domain.msgs.AtMsgs;
import com.example.main.dto.*;
import com.example.main.service.AtMsgsService;
import com.example.main.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AtMsgsApiController {

    private final AtMsgsService atMsgsService;
    private final CustomerService customerService;

    @PostMapping("/api/v1/at-msgs")
    public Integer save(@RequestBody AtMsgsSaveRequestDto requestDto) {
        return atMsgsService.save(requestDto);
    }

    @PostMapping("/api/v1/multi-at-msgs")
    public List<AtMsgs> saveAll(@RequestBody MultiAtMsgsSaveRequestDto requestDto) {
        return atMsgsService.saveAll(requestDto);
    }
    @PostMapping("/api/v1/multi-at-msgs/list")
    public List<AtMsgs> saveAllList(@RequestBody MultiAtMsgsSaveListRequestDto requestDto) {
        customerService.findAll();
        return atMsgsService.saveAll(requestDto);
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