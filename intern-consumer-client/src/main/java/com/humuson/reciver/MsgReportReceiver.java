package com.humuson.reciver;

import com.google.gson.Gson;
import com.humuson.agent.dto.AtReportSaveRequestDto;
import com.humuson.agent.dto.MtReportSaveRequestDto;
import com.humuson.utility.ApiCallCC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MsgReportReceiver {

    /*@KafkaListener(topics = "${kafka.at.report.topic.name}", groupId = "${kafka.at.report.topic.group.name}", containerFactory = "kafkaListenerContainerFactory")
    public void atReportListenr(@Payload List<String> messages) {
        log.info("At Report Topic Listener : {}", messages);

        Gson gson = new Gson();
        messages.forEach(message -> {
            log.info("message : {}", message);
            AtReportSaveRequestDto atReport = gson.fromJson(message, AtReportSaveRequestDto.class);
            String status = null;
            try {
                status = ApiCallCC.post(atReport.getEtc2(), message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("status is {}", status);
        });
    }*/

    private final KafkaListenerEndpointRegistry registry;

    @KafkaListener(topics = "${kafka.at.report.topic.name}", groupId = "${kafka.at.report.topic.group.name}", containerFactory = "kafkaListenerContainerFactory")
    public void atReportListenr(@Payload String message) {
        Gson gson = new Gson();
        AtReportSaveRequestDto atReport = gson.fromJson(message, AtReportSaveRequestDto.class);
        String status = ApiCallCC.post(atReport.getEtc2(), message);
        log.info("status is {}", status);
        if(status.equals("200")) {
            registry.destroy();
            registry.start();
//            registry.stop();
            log.info("success");
        } else {
            throw new RuntimeException("failed");
        }
    }

    /*@KafkaListener(topics = "${kafka.mt.report.topic.name}", groupId = "${kafka.mt.report.topic.group.name}", containerFactory = "kafkaListenerContainerFactory")
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
    }*/
}
