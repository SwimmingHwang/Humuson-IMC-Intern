package com.humuson.controller;

import com.humuson.domain.entity.AtCampaign;
import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.TemplateInfo;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.repository.AtMsgsRepository;
import com.humuson.dto.at.AtCampaignSaveRequestDto;
import com.humuson.dto.at.MultiAtMsgsSaveRequestDto;
import com.humuson.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Tag(name="대량 발송 관리", description = "대량 발송을 관리합니다.")
@RequiredArgsConstructor
@RestController
public class AtCampaignController {

    private final TemplateInfoService templateInfoService;
    private final AtCampaignService atCampaignService;
    private final CustomerService customerService;
    private final ProfileService profileService;
    private final UserService userService;
    private final AtMsgsService atMsgsService;

    @Operation(summary = "알림톡 캠페인 생성", description = "알림톡 캠페인을 예약합니다.")
    @PostMapping("/api/v1/at-campaign")
    public String save(@RequestBody AtCampaignSaveRequestDto requestDto, Authentication authentication){


//        private String msg;
//        private String phoneNumber;
//        private String templateCode;
//        private String reservedDate;
//        private List<List<String>> customerList;
//        private List<Integer> varCheckList;

//        @Transactional
//        public List<AtMsgs> saveAll(MultiAtMsgsSaveRequestDto requestDto){
//            // requestDto 를 AtMsgs 리스트로 변환
//            List<AtMsgs> atMsgs = requestDto.toEntity();
//            return atMsgsRepository.saveAll(atMsgs);
//        }

//        requestDto.toEntity
        TemplateInfo templateInfo = templateInfoService.findByTemplateContent(requestDto.getTemplateContent());

        /* AT CAMPAIGN 생성 */
        requestDto.setTemplateInfoC(templateInfo);
        log.info(requestDto.toString());
        AtCampaign atCampaign = requestDto.toEntity();

        String statusCode = "";
        try{
            atCampaignService.save(atCampaign);
            statusCode = "200"; //성공


            /* AT MSG 생성 */
            // TODO : At msgs save 로직 구현하기 + count설정해주기
            List<Long> idList = requestDto.getCustomers().stream().map(Long::parseLong).collect(Collectors.toList());

            Set<Customer> customers = customerService.findAllById(idList);
            List<List<String>> customerList = new ArrayList<>();
            for(Customer customer : customers){
                List<String> custom = new ArrayList<>();
                custom.add("82"+customer.getPhoneNumber().substring(1));
                customerList.add(custom);
            }

            String templateCode = templateInfo.getTemplateCode();
            MultiAtMsgsSaveRequestDto multiAtMsgsSaveRequestDto = new MultiAtMsgsSaveRequestDto(requestDto.getMsg(), templateCode, requestDto.getReservedDate(), customerList);

            atMsgsService.saveAll(multiAtMsgsSaveRequestDto);
        } catch (Exception e){
            log.error(e.toString());
            statusCode="500"; // db insert실패
        }
        return statusCode;
    }
}
