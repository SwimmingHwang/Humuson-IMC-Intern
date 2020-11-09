package com.example.main.controller;

import com.example.main.dto.FtMsgsListResponseDto;
import com.example.main.dto.FtMsgsResponseDto;
import com.example.main.dto.FtMsgsSaveRequestDto;
import com.example.main.dto.FtMsgsUpdateRequestDto;
import com.example.main.service.FtMsgsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FtMsgsApiController {

    private final FtMsgsService ftMsgsService;

    @PostMapping("/api/v1/ft-msgs")
    public Integer save(@RequestBody FtMsgsSaveRequestDto requestDto) {
        return ftMsgsService.save(requestDto);
    }

    @PutMapping("/api/v1/ft-msgs/{id}")
    public Integer update(@PathVariable Integer id, @RequestBody FtMsgsUpdateRequestDto requestDto) {
        return ftMsgsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/ft-msgs/{id}")
    public Integer delete(@PathVariable Integer id) {
        ftMsgsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/ft-msgs/{id}")
    public FtMsgsResponseDto findById(@PathVariable Integer id) {
        return ftMsgsService.findById(id);
    }

    @GetMapping("/api/v1/ft-msgs/list")
    public List<FtMsgsListResponseDto> findAll() {
        return ftMsgsService.findAllDesc();
    }
}