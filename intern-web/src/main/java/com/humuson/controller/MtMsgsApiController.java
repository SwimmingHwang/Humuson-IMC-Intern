package com.humuson.controller;

import com.google.gson.Gson;
import com.humuson.call.ApiCall;
import com.humuson.domain.msgs.MtMsgs;
import com.humuson.dto.mt.*;
import com.humuson.service.CustomerService;
import com.humuson.service.MtMsgsService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name="문자톡", description = "문자톡 메시지 레코드를 관리합니다.")
@Slf4j
@RequiredArgsConstructor
@RestController
public class MtMsgsApiController {

    private final MtMsgsService mtMsgsService;
    private final CustomerService customerService;
//    @Operation(summary="문자톡 생성", description = "문자톡 메시지 레코드를 DB에 insert & Server API로 데이터 전송")
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
//    @Operation(summary="문자톡 생성(파일로)", description = "문자톡 메시지 레코드들(파일로 읽은)을 DB에 insert & Server API로 데이터 전송")
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
//    @Operation(summary="문자톡 생성(주소록으로)", description = "문자톡 메시지 레코드들(고객 주소록 참조한)을 DB에 insert & Server API로 데이터 전송")

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
//    @Operation(summary="문자톡 수정", description = "문자톡 메시지 레코드 수정")
    @PutMapping("/api/v1/mt-msgs/{id}")
    public Integer update(@PathVariable Integer id, @RequestBody MtMsgsUpdateRequestDto requestDto) {
        return mtMsgsService.update(id, requestDto);
    }
//    @Operation(summary="문자톡 삭제", description = "문자톡 메시지 레코드 삭제")
    @DeleteMapping("/api/v1/mt-msgs/{id}")
    public Integer delete(@PathVariable Integer id) {
        mtMsgsService.delete(id);
        return id;
    }
//    @Operation(summary="문자톡 조회", description = "문자톡 메시지 레코드 조회")
    @GetMapping("/api/v1/mt-msgs/{id}")
    public MtMsgsResponseDto findById(@PathVariable Integer id) {
        return mtMsgsService.findById(id);
    }
//    @Operation(summary="문자톡 조회", description = "문자톡 메시지 레코드 조회")
    @GetMapping("/api/v1/mt-msgs/list")
    public List<MtMsgsListResponseDto> findAll() {
        return mtMsgsService.findAllDesc();
    }
}