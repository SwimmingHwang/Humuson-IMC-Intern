package com.humuson.controller;

import com.humuson.domain.entity.AtCampaign;
import com.humuson.dto.at.AtCampaignSaveRequestDto;
import com.humuson.service.AtCampaignService;
import com.humuson.service.CustomerService;
import com.humuson.service.TemplateInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Tag(name="대량 발송 관리", description = "대량 발송을 관리합니다.")
@RequiredArgsConstructor
@RestController
public class AtCampaignController {

    private final TemplateInfoService templateInfoService;
    private final AtCampaignService atCampaignService;
    private final CustomerService customerService;

    @Operation(summary = "알림톡 캠페인 생성", description = "알림톡 캠페인을 예약합니다.")
    @PostMapping("/api/v1/at-campaign")
    public AtCampaign save(@RequestBody AtCampaignSaveRequestDto requestDto){
        // TODO : At msgs save 로직 구현하기 + count설정해주기
        long count = 0;

        List<Long> idList = requestDto.getCustomers().stream().map(Long::parseLong).collect(Collectors.toList());

//        customerService.findAllById(idList);

//        requestDto.toEntity
        requestDto.setTemplateInfoC(templateInfoService.findByTemplateName(requestDto.getTemplateInfo()));
        log.info(requestDto.toString());
        // TODO : reserved date parsning 여기서 처리해줘도 될 듯.. !! -> js에서 힘들게 파싱하지말기 sy
        AtCampaign atCampaign = requestDto.toEntity();

        return atCampaignService.save(atCampaign);
    }
}
