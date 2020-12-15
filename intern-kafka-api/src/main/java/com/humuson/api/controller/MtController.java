package com.humuson.api.controller;

import com.humuson.agent.dto.MtMsgsSaveRequestDto;
import com.humuson.api.Producer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Tag(name="문자 API")
@RequiredArgsConstructor
@Controller
public class MtController {

    private final Producer producer;

    @Operation(summary = "문자 메시지 리스트 전송")
    @PostMapping(value = "/api/mt-msgs",produces = "application/json; charset=utf8")
    @ResponseBody
    public String apiMtMsgs(@RequestBody List<MtMsgsSaveRequestDto> requestDto) {
        String stringStatusCode = "";
        stringStatusCode = producer.batchMtProduce(requestDto);
        return stringStatusCode;//200 or 9000
    }
}