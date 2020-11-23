package com.humuson.controller;

import com.humuson.dto.ft.FtMsgsListResponseDto;
import com.humuson.dto.ft.FtMsgsResponseDto;
import com.humuson.dto.ft.FtMsgsSaveRequestDto;
import com.humuson.dto.ft.FtMsgsUpdateRequestDto;
import com.humuson.service.FtMsgsService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@Tag(name="친구톡", description = "친구톡 메시지 레코드를 관리합니다.")
@RequiredArgsConstructor
@RestController
public class FtMsgsApiController {

    private final FtMsgsService ftMsgsService;
//    @Operation(summary = "친구톡 생성", description = "친구톡 메시지 레코드 생성")
    @PostMapping("/api/v1/ft-msgs")
    public Integer save(@RequestBody FtMsgsSaveRequestDto requestDto) {
        return ftMsgsService.save(requestDto);
    }
//    @Operation(summary = "친구톡 생성(파일로)", description = "친구톡 메시지 레코드 수정")
    @PutMapping("/api/v1/ft-msgs/{id}")
    public Integer update(@PathVariable Integer id, @RequestBody FtMsgsUpdateRequestDto requestDto) {
        return ftMsgsService.update(id, requestDto);
    }
//    @Operation(summary = "친구톡 생성(주소록으로)", description = "친구톡 메시지 레코드 삭제")
    @DeleteMapping("/api/v1/ft-msgs/{id}")
    public Integer delete(@PathVariable Integer id) {
        ftMsgsService.delete(id);
        return id;
    }
//    @Operation(summary = "친구톡 조회", description = "친구톡 메시지 레코드 조회")
    @GetMapping("/api/v1/ft-msgs/{id}")
    public FtMsgsResponseDto findById(@PathVariable Integer id) {
        return ftMsgsService.findById(id);
    }
//    @Operation(summary = "친구톡 리스트 조회", description = "친구톡 메시지 레코드들 조회")
    @GetMapping("/api/v1/ft-msgs/list")
    public List<FtMsgsListResponseDto> findAll() {
        return ftMsgsService.findAllDesc();
    }
}