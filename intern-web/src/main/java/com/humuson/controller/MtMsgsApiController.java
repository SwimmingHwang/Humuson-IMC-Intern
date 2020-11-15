package com.humuson.controller;

import com.humuson.dto.MtMsgsListResponseDto;
import com.humuson.dto.MtMsgsResponseDto;
import com.humuson.dto.MtMsgsSaveRequestDto;
import com.humuson.dto.MtMsgsUpdateRequestDto;
import com.humuson.service.MtMsgsService;
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