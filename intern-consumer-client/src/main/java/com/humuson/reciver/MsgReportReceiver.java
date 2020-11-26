package com.humuson.reciver;

import com.google.gson.Gson;
import com.humuson.agent.domain.entity.AtMsgs;
import com.humuson.agent.domain.entity.AtReport;
import com.humuson.agent.dto.AtReportSaveRequestDto;
import com.humuson.agent.dto.MtMsgsSaveRequestDto;
import com.humuson.agent.dto.MtReportSaveRequestDto;
import com.humuson.agent.service.AtMsgsJdbcService;
import com.humuson.agent.service.AtMsgsService;
import com.humuson.agent.service.FtMsgsService;
import com.humuson.agent.service.MtMsgsService;
import com.humuson.utility.ApiCallCC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MsgReportReceiver {

    private final AtMsgsService atMsgsService;
    private final AtMsgsJdbcService atMsgsJdbcService;
    private final FtMsgsService ftMsgsService;
    private final MtMsgsService mtMsgsService;

    @KafkaListener(topics = "${kafka.at.report.topic.name}", groupId = "${kafka.at.report.topic.group.name}")
    public void atReportlistenr(@Payload List<String> messages) {
        log.info("At Report Topic Listener : {}", messages);

        Gson gson = new Gson();
        messages.forEach(message -> {
            log.info("At Topic Listner : {}", message);

            AtReportSaveRequestDto atReport = gson.fromJson(message, AtReportSaveRequestDto.class);
            log.info("url : {}", atReport.getEtc2());
            String status = null;
            try {
                status = ApiCallCC.post(atReport.getEtc2(), message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("status is {}", status);
//            ApiCall.get(atReport.getEtc2());
        });

    }

    @KafkaListener(topics = "${kafka.mt.report.topic.name}", groupId = "${kafka.mt.report.topic.group.name}")
    public void mtLoglistenr(@Payload List<String> messages) {
        log.info("Mt Report Topic Listener : {}", messages);

        Gson gson = new Gson();
        messages.forEach(message -> {
            log.info("Mt Topic Listner : {}", message);

            MtReportSaveRequestDto mtReport = gson.fromJson(message, MtReportSaveRequestDto.class);
            log.info("url : {}", mtReport.getEtc2());
            String status = null;
            try {
                status = ApiCallCC.post(mtReport.getEtc2(), message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("status is {}", status);
        });
    }
}
