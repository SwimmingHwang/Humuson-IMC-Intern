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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    private final AtMsgsJdbcService atMsgsJdbcService;

    @Operation(summary = "알림톡 캠페인 생성", description = "알림톡 캠페인을 예약합니다.")
    @PostMapping("/api/v1/at-campaign")
    public String save(@RequestBody AtCampaignSaveRequestDto requestDto, Authentication authentication){

        TemplateInfo templateInfo = templateInfoService.findByTemplateContent(requestDto.getTemplateContent());

        /* AT CAMPAIGN 생성 */
        requestDto.setTemplateInfoC(templateInfo);
        AtCampaign atCampaign = requestDto.toEntity();
        atCampaign.setCount(requestDto.getCustomers().size());
        atCampaign.setCustomers(String.join(",",requestDto.getCustomers()));
        atCampaign.setVars(requestDto.getVars());
        AtCampaign savedAtCampaign = atCampaignService.save(atCampaign);
        long campId = savedAtCampaign.getId();
        String statusCode = "";
        try{
            /* AT MSG 생성 */
            List<Long> idList = requestDto.getCustomers().stream().distinct().map(Long::parseLong).collect(Collectors.toList());

            Set<Customer> customers = customerService.findAllById(idList);
            List<List<String>> customerList = new ArrayList<>();
            for(Customer customer : customers){
                List<String> custom = Arrays.asList(customer.getUserId(),
                        customer.getName(),
                        "82"+customer.getPhoneNumber().substring(1),
                         customer.getAddress(),
                        customer.getEtc1() == null? "": customer.getEtc1(),
                        customer.getEtc2() == null? "": customer.getEtc2(),
                        customer.getEtc3() == null? "": customer.getEtc3());
                customerList.add(custom);
            }
            String templateCode = templateInfo.getTemplateCode();
            MultiAtMsgsSaveRequestDto multiAtMsgsSaveRequestDto = new MultiAtMsgsSaveRequestDto(requestDto.getMsg(),
                    templateCode, requestDto.getReservedDate(), customerList, savedAtCampaign);

            atMsgsJdbcService.saveAll(multiAtMsgsSaveRequestDto.toEntity());
            atMsgsService.updateEtc2();
            statusCode = "200"; //성공
        } catch (Exception e){
            statusCode="500"; // db insert실패
            log.error(e.toString());
            log.error(e.getMessage());
            log.error(e.getCause().toString());
        }
        return statusCode;
    }
    @Operation(summary = "알림톡 캠페인 수정", description = "알림톡 캠페인 예약 정보를 수정합니다.")
    @PutMapping("/api/v1/at-campaign/{id}")
    public String update(@PathVariable Long id, @RequestBody AtCampaignSaveRequestDto requestDto){
        atCampaignService.delete(id); // 캠페인 정보랑 그 자식들도 다 삭제
        TemplateInfo templateInfo = templateInfoService.findByTemplateContent(requestDto.getTemplateContent());

        /* AT CAMPAIGN 생성 */
        requestDto.setTemplateInfoC(templateInfo);
        AtCampaign atCampaign = requestDto.toEntity();
        atCampaign.setCount(requestDto.getCustomers().size());
        atCampaign.setCustomers(String.join(",",requestDto.getCustomers()));
        AtCampaign savedAtCampaign = atCampaignService.save(atCampaign);
        long campId = savedAtCampaign.getId();
        String statusCode = "";
        try{
            /* AT MSG 생성 */
            List<Long> idList = requestDto.getCustomers().stream().distinct().map(Long::parseLong).collect(Collectors.toList());

            Set<Customer> customers = customerService.findAllById(idList);
            List<List<String>> customerList = new ArrayList<>();
            for(Customer customer : customers){
                List<String> custom = Arrays.asList(customer.getUserId(),
                        customer.getName(),
                        "82"+customer.getPhoneNumber().substring(1),
                        customer.getAddress(),
                        customer.getEtc1() == null? "": customer.getEtc1(),
                        customer.getEtc2() == null? "": customer.getEtc2(),
                        customer.getEtc3() == null? "": customer.getEtc3());
                customerList.add(custom);
            }

            String templateCode = templateInfo.getTemplateCode();
            MultiAtMsgsSaveRequestDto multiAtMsgsSaveRequestDto = new MultiAtMsgsSaveRequestDto(requestDto.getMsg(),
                    templateCode, requestDto.getReservedDate(), customerList, savedAtCampaign);

            atMsgsJdbcService.saveAll(multiAtMsgsSaveRequestDto.toEntity());
            atMsgsService.updateEtc2();
            statusCode = "200"; //성공

        } catch (Exception e){
            log.error(e.getMessage());
            log.error(e.getCause().toString());
            statusCode="500"; // db insert실패
        }
        return statusCode;
    }
    @Operation(summary="알림톡 캠페인 삭제", description = "알림톡 캠페인 및 관련 메시지 삭제")
    @DeleteMapping("/api/v1/at-campaign/{id}")
    public long delete(@PathVariable long id) {
        // TODO : 관련 메시지들도 삭제 되어야 함.
        atCampaignService.delete(id);
        return id;
    }
}
