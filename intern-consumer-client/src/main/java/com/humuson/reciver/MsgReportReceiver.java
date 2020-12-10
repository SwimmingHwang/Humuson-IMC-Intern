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

    private final KafkaListenerEndpointRegistry registry;

    @KafkaListener(topics = "${kafka.at.report.topic.name}", groupId = "${kafka.at.report.topic.group.name}", containerFactory = "kafkaListenerContainerFactory")
    public void atReportListener(@Payload String message) {
        Gson gson = new Gson();
        AtReportSaveRequestDto atReport = gson.fromJson(message, AtReportSaveRequestDto.class);
        String status = ApiCallCC.post(atReport.getEtc2(), message);
        log.info("At Topic Listner {}", status);
        if(status.equals("200")) {
            registry.destroy();
            registry.start();
            log.info("success");
        } else {
            throw new RuntimeException("failed");
        }
    }

    @KafkaListener(topics = "${kafka.mt.report.topic.name}", groupId = "${kafka.mt.report.topic.group.name}", containerFactory = "kafkaListenerContainerFactory")
    public void mtReportListener(@Payload String message) {
        Gson gson = new Gson();
        MtReportSaveRequestDto mtReport = gson.fromJson(message, MtReportSaveRequestDto.class);
        String status = ApiCallCC.post(mtReport.getEtc2(), message);
        log.info("Mt Topic Listner {}", status);
        if(status.equals("200")) {
            registry.destroy();
            registry.start();
            log.info("success");
        } else {
            throw new RuntimeException("failed");
        }
    }
}
