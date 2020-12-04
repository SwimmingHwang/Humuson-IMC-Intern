package com.humuson.controller;

import com.google.gson.Gson;
import com.humuson.call.ApiCall;
import com.humuson.domain.msgs.MtMsgs;
import com.humuson.dto.mt.*;
import com.humuson.dto.report.MtReportListDashboardResponseDto;
import com.humuson.service.CustomerService;
import com.humuson.service.MtMsgsJdbcService;
import com.humuson.service.MtMsgsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="문자톡", description = "문자톡 메시지 레코드를 관리합니다.")
@Slf4j
@RequiredArgsConstructor
@RestController
public class MtMsgsApiController {

    private final MtMsgsService mtMsgsService;
    private final MtMsgsJdbcService mtMsgsJdbcService;
    private final CustomerService customerService;

    @Operation(summary="문자톡 생성", description = "문자톡 메시지 레코드를 DB에 insert & Server API로 데이터 전송")
    @PostMapping("/api/v1/mt-msgs")
    public String save(@RequestBody MtMsgsSaveRequestDto requestDto) {
        try{
            mtMsgsService.save(requestDto);
        } catch(Exception e){
            log.info("ERROR : DB INSERT ERROR"); return "300";
        }
        return "200";
    }
    @Operation(summary="문자톡 생성(파일로)", description = "문자톡 메시지 레코드들(파일로 읽은)을 DB에 insert & Server API로 데이터 전송")
    @PostMapping(value = "/api/v1/multi-mt-msgs", produces = "application/json; charset=utf8")
    public String saveAll(@RequestBody MultiMtMsgsSaveRequestDto requestDto) {
        try{
            mtMsgsService.saveAll(requestDto);
        } catch(Exception e){
            log.info("ERROR : DB INSERT ERROR"); return "300";
        }
        return "200";
    }
    @Operation(summary="문자톡 생성(주소록으로)", description = "문자톡 메시지 레코드들(고객 주소록 참조한)을 DB에 insert & Server API로 데이터 전송")
    @PostMapping("/api/v1/multi-mt-msgs/list")
    public String saveAllList(@RequestBody MultiMtMsgsSaveListRequestDto requestDto) {
        try{
            mtMsgsJdbcService.saveAllList(requestDto);
        } catch(Exception e){
            log.info("ERROR : DB INSERT ERROR"); return "300";
        }
        return "200";
    }
    @Operation(summary="문자톡 수정", description = "문자톡 메시지 레코드 수정")
    @PutMapping("/api/v1/mt-msgs/{id}")
    public Integer update(@PathVariable Integer id, @RequestBody MtMsgsUpdateRequestDto requestDto) {
        return mtMsgsService.update(id, requestDto);
    }
    @Operation(summary="문자톡 삭제", description = "문자톡 메시지 레코드 삭제")
    @DeleteMapping("/api/v1/mt-msgs/{id}")
    public Integer delete(@PathVariable Integer id) {
        mtMsgsService.delete(id);
        return id;
    }
    @Operation(summary="문자톡 조회", description = "문자톡 메시지 레코드 조회")
    @GetMapping("/api/v1/mt-msgs/{id}")
    public MtMsgsResponseDto findById(@PathVariable Integer id) {
        return mtMsgsService.findById(id);
    }
    
    /*@Operation(summary="문자톡 리스트 조회", description = "모든 문자톡 메시지의 레코드 조회")
    @GetMapping("/api/v1/mt-msgs/list")
    public List<MtMsgsListResponseDto> findAll() {
        return mtMsgsService.findAllDesc();
    }*/

    @Operation(summary="문자톡 리스트 조회", description = "모든 문자톡 메시지의 레코드 조회")
    @GetMapping("/api/v1/mt-msgs/list")
    public List<MtMsgs> mtMsgsfindAll() {
        return mtMsgsService.findAll();
    }

    @Operation(summary="필요한 문자 결과 정보 조회", description = "대시보드에 필요한 문자 메세지의 상태를 조회")
    @GetMapping("/api/v1/mt-msgs-info/list")
    public List<MtMsgsListDashboardResponseDto> mtMsgsFindInfoList() {
        return mtMsgsService.findInfoList();
    }
}