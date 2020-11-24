package com.humuson.controller;

import com.humuson.service.AtMsgsJdbcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name="알림톡 상태 수정", description = "알림톡의 상태를 수정합니다.")
@RequiredArgsConstructor
@RestController
@Slf4j
public class AtMsgsReportApiController {

    private final AtMsgsJdbcService atMsgsJdbcService;
    
//    @Operation(summary="알림톡 status 수정", description = "알림톡 메시지 상태 수정")
    @PutMapping("/api/v1/at-msgs-report/{id}")
    public void updateAllStatus(@PathVariable Integer id, @RequestBody List<Integer> idList) {
        atMsgsJdbcService.updateAllStatus(idList);
    }

}