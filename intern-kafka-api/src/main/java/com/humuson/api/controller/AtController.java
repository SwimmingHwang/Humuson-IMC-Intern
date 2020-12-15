package com.humuson.api.controller;

import com.humuson.agent.dto.AtMsgsSaveRequestDto;
import com.humuson.api.Producer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name="알림톡 API")
@RequiredArgsConstructor
@Controller
public class AtController {

    private final Producer producer;

    @Operation(summary = "알림톡 메시지 리스트 전송")
    @PostMapping(value = "/api/at-msgs",produces = "application/json; charset=utf8")
    @ResponseBody
    public String apiAtMsgs(@RequestBody List<AtMsgsSaveRequestDto> requestDto) {
        String stringStatusCode = "";
        stringStatusCode = producer.batchAtProduce(requestDto);
        return stringStatusCode;//200 or 9000
    }
}