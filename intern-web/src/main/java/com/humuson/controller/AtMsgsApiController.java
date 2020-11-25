package com.humuson.controller;

import com.google.gson.Gson;
import com.humuson.call.ApiCall;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.dto.at.*;
import com.humuson.service.AtMsgsJdbcService;
import com.humuson.service.AtMsgsService;
import com.humuson.service.CustomerService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

//@Tag(name="알림톡", description = "알림톡 메시지 레코드를 관리합니다.")
@RequiredArgsConstructor
@RestController
@Slf4j
public class AtMsgsApiController {

    private final AtMsgsService atMsgsService;
    private final AtMsgsJdbcService atMsgsJdbcService;
    private final CustomerService customerService;

//    @Operation(summary="알림톡 생성", description = "알림톡 메시지 레코드를 DB에 insert & Server API로 데이터 전송")
    @PostMapping("/api/v1/at-msgs")
    public String save(@RequestBody AtMsgsSaveRequestDto requestDto) {
        // TODO : url 끝에 id 추가 어떻게 해야 할까?
        try{
            atMsgsService.save(requestDto);
        } catch(Exception e){
            log.info("ERROR : DB INSERT ERROR"); return "300";
        }
//        return statusCode;
        return "200";
    }
//    @Operation(summary="알림톡 생성(파일로)", description = "알림톡 메시지 레코드들(파일로 읽은)을 DB에 insert & Server API로 데이터 전송")
    @PostMapping(value = "/api/v1/multi-at-msgs",produces = "application/json; charset=utf8")
    public String saveAll(@RequestBody MultiAtMsgsSaveRequestDto requestDto) {
        Gson gson = new Gson();
        List<AtMsgs> atMsgs = requestDto.toEntity();
        String reqData = gson.toJson(atMsgs);
        log.info("Request Data : " +reqData);
        String statusCode = ApiCall.post("http://localhost:8082/api/at-msgs",reqData);
        log.info("statusCode :"+statusCode);

        if (statusCode.equals("200")){
            atMsgsService.saveAll(requestDto);
        }
        return statusCode;
    }
//    @Operation(summary="알림톡 생성(주소록으로)", description = "알림톡 메시지 레코드들(고객 주소록 참조한)을 DB에 insert & Server API로 데이터 전송")
    @PostMapping("/api/v1/multi-at-msgs/list")
    public String saveAllList(@RequestBody MultiAtMsgsSaveListRequestDto requestDto) {
        List<AtMsgs> atMsgs = requestDto.toEntity(customerService.findAll());
        atMsgsJdbcService.saveAllList(requestDto);
        return "200";
    }
//    @Operation(summary="알림톡 수정", description = "알림톡 메시지 레코드 수정")
    @PutMapping("/api/v1/at-msgs/{id}")
    public Integer update(@PathVariable Integer id, @RequestBody AtMsgsUpdateRequestDto requestDto) {
        return atMsgsService.update(id, requestDto);
    }
//    @Operation(summary="알림톡 삭제", description = "알림톡 메시지 레코드 삭제")
    @DeleteMapping("/api/v1/at-msgs/{id}")
    public Integer delete(@PathVariable Integer id) {
        atMsgsService.delete(id);
        return id;
    }
//    @Operation(summary="알림톡 조회", description = "알림톡 메시지 레코드 조회")
    @GetMapping("/api/v1/at-msgs/{id}")
    public AtMsgsResponseDto findById(@PathVariable Integer id) {
        return atMsgsService.findById(id);
    }
    public List<AtMsgsListResponseDto> findAll() {
        return atMsgsService.findAllDesc();
    }
}