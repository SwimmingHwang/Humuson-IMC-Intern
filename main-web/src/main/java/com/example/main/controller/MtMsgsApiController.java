package com.example.main.controller;

import com.example.main.dto.MtMsgsListResponseDto;
import com.example.main.dto.MtMsgsResponseDto;
import com.example.main.dto.MtMsgsSaveRequestDto;
import com.example.main.dto.MtMsgsUpdateRequestDto;
import com.example.main.service.MtMsgsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MtMsgsApiController {

    private final MtMsgsService mtMsgsService;

    @PostMapping("/api/v1/mt-msgs")
    public Integer save(@RequestBody MtMsgsSaveRequestDto requestDto) {
        return mtMsgsService.save(requestDto);
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