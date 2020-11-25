package com.humuson.job;

import com.google.gson.Gson;
import com.humuson.call.ApiCall;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.msgs.MtMsgs;
import com.humuson.dto.at.AtMsgsSaveRequestDto;
import com.humuson.dto.at.AtMsgsUpdateStatusRequestDto;
import com.humuson.dto.at.MultiAtMsgsSaveListRequestDto;
import com.humuson.dto.mt.MtMsgsSaveRequestDto;
import com.humuson.service.AtMsgsService;
import com.humuson.service.CustomerService;
import com.humuson.service.MtMsgsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
@Slf4j
public class ScheduledTasks {

    private final AtMsgsService atMsgsService;
    private final MtMsgsService mtMsgsService;
    private final CustomerService customerService;

    @Async
    @Scheduled(initialDelay = 1000, fixedRate  = 10000)
    public void updateStatusrunEvery10Sec(){
        try{
            log.info("스케쥴러 : 시작");
            List<AtMsgs> atMsgsList = atMsgsService.findAllByReservedDate();
            List<MtMsgs> mtMsgsList = mtMsgsService.findAllByReservedDate();

            log.info("스케쥴러 : select 완료");

            List<AtMsgsSaveRequestDto> atMsgsSaveRequestDtoList= new ArrayList<>();
            List<MtMsgsSaveRequestDto> mtMsgsSaveRequestDtoList= new ArrayList<>();

            log.info("스케쥴러 : atMsgsList"+atMsgsList);
            log.info("스케쥴러 : mtMsgsList"+atMsgsList);

            if (!atMsgsList.isEmpty()){

                atMsgsList.forEach(row ->{
                    AtMsgsSaveRequestDto atMsgsSaveRequestDto = new AtMsgsSaveRequestDto(row.getMsg(),row.getPhoneNumber(),
                            row.getTemplateCode(), row.getReservedDate());
                    //    for (int i=0; i<12500; i++) //10만건 테스트용
                    atMsgsSaveRequestDtoList.add(atMsgsSaveRequestDto);
                });

                Gson gson = new Gson();
                String reqData = gson.toJson(atMsgsSaveRequestDtoList);
                log.info("Request Data : " +reqData);
                String statusCode = ApiCall.post("http://localhost:8082/api/at-msgs",reqData);
                log.info("statusCode :"+statusCode);

                if (statusCode.equals("200")){
                    log.info("API POST REQUEST 성공");

                    log.info("스케쥴러 : AT Update 시작");

                    // 성공했으면  status update 2
                    atMsgsList.forEach(row ->{
                        // TODO : update batch로 작성할 것
                        log.info("스케쥴러 : AT Update 진행중");
                        atMsgsService.updateStatus(row.getId(), "2");
                    });
                    log.info("스케쥴러 : AT Update 끝");

                }
                else{
                    log.info("API POST ERROR");
                }
            }


            if (!mtMsgsList.isEmpty()){

                mtMsgsList.forEach(row ->{
                    MtMsgsSaveRequestDto mtMsgsSaveRequestDto = new MtMsgsSaveRequestDto(row.getMsg(), row.getPhoneNumber(), row.getAdFlag(),
                            row.getMtType(), row.getReservedDate(), row.getCallback());
                    //    for (int i=0; i<12500; i++) //10만건 테스트용
                    mtMsgsSaveRequestDtoList.add(mtMsgsSaveRequestDto);
                });

                Gson gson = new Gson();
                String reqData = gson.toJson(mtMsgsSaveRequestDtoList);
                log.info("Request Data : " +reqData);
                String statusCode = ApiCall.post("http://localhost:8082/api/mt-msgs",reqData);
                log.info("statusCode :"+statusCode);

                if (statusCode.equals("200")){
                    log.info("API POST REQUEST 성공");

                    log.info("스케쥴러 : MT Update 시작");

                    mtMsgsList.forEach(row ->{
                        // TODO : update batch로 작성할 것
                        log.info("스케쥴러 : MT Update 진행중");
                        mtMsgsService.updateStatus(row.getId(), "2");
                    });
                    log.info("스케쥴러 : MT Update 끝");
                }
                else{
                    log.info("API POST ERROR");
                }
            }
            log.info("runEvery10Sec");
        }catch(Exception e){
            log.error("Update Status ERROR "+e);
            log.info("Update Status ERROR "+e);
        }

    }
}