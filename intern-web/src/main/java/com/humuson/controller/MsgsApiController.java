package com.humuson.controller;

import com.humuson.dto.MsgsListResponseDto;
import com.humuson.dto.MsgsResponseDto;
import com.humuson.dto.MsgsSaveRequestDto;
import com.humuson.dto.MsgsUpdateRequestDto;
import com.humuson.service.MsgsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MsgsApiController {

    private final MsgsService msgsService;

    @PostMapping("/api/v1/msgs")
    public Integer save(@RequestBody MsgsSaveRequestDto requestDto) {
        return msgsService.save(requestDto);
    }

    @PutMapping("/api/v1/msgs/{id}")
    public Integer update(@PathVariable Integer id, @RequestBody MsgsUpdateRequestDto requestDto) {
        return msgsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/msgs/{id}")
    public Integer delete(@PathVariable Integer id) {
        msgsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/msgs/{id}")
    public MsgsResponseDto findById(@PathVariable Integer id) {
        return msgsService.findById(id);
    }

    @GetMapping("/api/v1/msgs/list")
    public List<MsgsListResponseDto> findAll() {
        return msgsService.findAllDesc();
    }
}