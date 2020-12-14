package com.humuson.job.scheduler;

import com.google.gson.Gson;
import com.humuson.call.ApiCall;
import com.humuson.domain.entity.AtCampaign;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.msgs.MtMsgs;
import com.humuson.dto.at.AtMsgsSaveRequestDto;
import com.humuson.dto.mt.MtMsgsSaveRequestDto;
import com.humuson.service.AtCampaignService;
import com.humuson.service.AtMsgsService;
import com.humuson.service.CustomerService;
import com.humuson.service.MtMsgsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
@Slf4j
public class ScheduledTasks {

    private final AtMsgsService atMsgsService;
    private final MtMsgsService mtMsgsService;
    private final AtCampaignService atCampaignService;

    @Transactional(rollbackFor = Exception.class)
//    @Scheduled(initialDelay = 1000, fixedDelay = 10000)
    public void updateStatusRunEvery10Sec()  throws Exception {
        log.info("스케쥴러 : 시작");
        try {
            List<AtMsgsSaveRequestDto> atMsgsList = atMsgsService.findAllByReservedDate();
            List<Integer> atMsgsIdList = atMsgsService.findAllIdByReservedDate();
            List <AtCampaign> atCampaignList = atCampaignService.findAllByReservedDate();
            log.info("AT 스케쥴러 : select 완료");
            log.info("AT 스케쥴러 : atMsgsList" + atMsgsList);

            if(!atCampaignList.isEmpty()){
                for(AtCampaign atCampaign:atCampaignList){
                    log.info(atCampaign.getAtMsgs().toString());
                    atCampaignService.updateStatus(atCampaign.getId(),"2");

                }
            }

            // TODO : api server error 인지 produce 실패 인지  status code 분리 필요
            if (!atMsgsIdList.isEmpty()) {
                atMsgsService.updateStatusList(atMsgsIdList);
            }

            if (!atMsgsList.isEmpty()) {
                Gson gson = new Gson();
                String reqData = gson.toJson(atMsgsList);
                log.info("AT Request Data : " + reqData);
                String statusCode = ApiCall.post("http://localhost:8082/api/at-msgs", reqData);
                log.info("AT Post Response Status Code :" + statusCode);
            }
        } catch(Exception e){
            log.error("AT Update Status ERROR "+e);
            throw new Exception();
        }

        try{

            List<MtMsgsSaveRequestDto> mtMsgsList =  mtMsgsService.findAllByReservedDate();
            List<Integer> mtMsgsIdList = mtMsgsService.findAllIdByReservedDate();

            log.info("MT 스케쥴러 : select 완료");
            log.info("MT 스케쥴러 : mtMsgsList" + mtMsgsList);

            // TODO : api server error 인지 produce 실패 인지  status code 분리 필요
            if (!mtMsgsIdList.isEmpty()) {
                mtMsgsService.updateStatusList(mtMsgsIdList);
            }

            if (!mtMsgsList.isEmpty()) {
                Gson gson = new Gson();
                String reqData = gson.toJson(mtMsgsList);
                log.info("MT Request Data : " + reqData);
                String statusCode = ApiCall.post("http://localhost:8082/api/mt-msgs", reqData);
                log.info("MT Post Response Status Code :" + statusCode);
            }
        } catch(Exception e){
            log.error("MT Update Status ERROR "+e);
            throw new Exception();
        }
        log.info("Run Every 10 Sec");
    }
}