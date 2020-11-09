package com.example.main.controller;

import com.example.main.dto.AtMsgsListResponseDto;
import com.example.main.dto.AtMsgsResponseDto;
import com.example.main.dto.AtMsgsSaveRequestDto;
import com.example.main.dto.AtMsgsUpdateRequestDto;
import com.example.main.service.AtMsgsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AtMsgsApiController {

    private final AtMsgsService atMsgsService;

    @PostMapping("/api/v1/at-msgs")
    public Integer save(@RequestBody AtMsgsSaveRequestDto requestDto) {
        return atMsgsService.save(requestDto);
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