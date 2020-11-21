package com.humuson.controller;

import com.humuson.dto.msg.MsgsListResponseDto;
import com.humuson.dto.msg.MsgsResponseDto;
import com.humuson.dto.msg.MsgsSaveRequestDto;
import com.humuson.dto.msg.MsgsUpdateRequestDto;
import com.humuson.service.MsgsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MsgsApiController {

    private final MsgsService msgsService;
    @Operation(hidden = true)
    @PostMapping("/api/v1/msgs")
    public Integer save(@RequestBody MsgsSaveRequestDto requestDto) {
        return msgsService.save(requestDto);
    }
    @Operation(hidden = true)
    @PutMapping("/api/v1/msgs/{id}")
    public Integer update(@PathVariable Integer id, @RequestBody MsgsUpdateRequestDto requestDto) {
        return msgsService.update(id, requestDto);
    }
    @Operation(hidden = true)
    @DeleteMapping("/api/v1/msgs/{id}")
    public Integer delete(@PathVariable Integer id) {
        msgsService.delete(id);
        return id;
    }
    @Operation(hidden = true)
    @GetMapping("/api/v1/msgs/{id}")
    public MsgsResponseDto findById(@PathVariable Integer id) {
        return msgsService.findById(id);
    }
    @Operation(hidden = true)
    @GetMapping("/api/v1/msgs/list")
    public List<MsgsListResponseDto> findAll() {
        return msgsService.findAllDesc();
    }
}