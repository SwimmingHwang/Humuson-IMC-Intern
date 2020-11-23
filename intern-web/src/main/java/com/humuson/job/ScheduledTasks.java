package com.humuson.job;

import com.google.gson.Gson;
import com.humuson.call.ApiCall;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.dto.at.AtMsgsSaveRequestDto;
import com.humuson.dto.at.AtMsgsUpdateStatusRequestDto;
import com.humuson.dto.at.MultiAtMsgsSaveListRequestDto;
import com.humuson.service.AtMsgsService;
import com.humuson.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class ScheduledTasks {

    private final AtMsgsService atMsgsService;
    private final CustomerService customerService;

    @Scheduled(fixedDelay = 10000)
    public void updateStatusrunEvery10Sec(){

        List<AtMsgs> atMsgsList = atMsgsService.findAllByReservedDate();
        List<AtMsgsSaveRequestDto> atMsgsSaveRequestDtoList= new ArrayList<>();
        if (!atMsgsList.isEmpty()){
            atMsgsList.forEach(row ->{
                atMsgsService.updateStatus(row.getId(), "2");
                //String msg, String phoneNumber, String templateCode, String reservedDate
                AtMsgsSaveRequestDto atMsgsSaveRequestDto = new AtMsgsSaveRequestDto(row.getMsg(),row.getPhoneNumber(),
                        row.getTemplateCode(), row.getReservedDate());
                for (int i=0; i<1250; i++)
                    atMsgsSaveRequestDtoList.add(atMsgsSaveRequestDto);
            });

            Gson gson = new Gson();
            String reqData = gson.toJson(atMsgsSaveRequestDtoList);
            log.info("Request Data : " +reqData);
            String statusCode = ApiCall.post("http://localhost:8082/api/at-msgs",reqData);
            log.info("statusCode :"+statusCode);

            if (statusCode.equals("200")){
                log.info("API POST REQUEST 성공");
            }
            else{
                log.info("API POST ERROR");
            }
        }
        log.info("runEvery10Sec");

//    @Scheduled(cron = "0 0 17 * * *")
//    public void runAt9EveryDay(){
//        log.info("runAt17EveryDay");
//    }
    }
}