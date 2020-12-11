package com.humuson.reciver;

import com.google.gson.Gson;
import com.humuson.acutator.KafkaHealthIndicator;
import com.humuson.agent.dto.AtReportSaveRequestDto;
import com.humuson.agent.dto.MtReportSaveRequestDto;
import com.humuson.utility.ApiCallCC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MsgReportReceiver {

//    private final KafkaListenerEndpointRegistry registry;

    @KafkaListener(topics = "${kafka.at.report.topic.name}", groupId = "${kafka.at.report.topic.group.name}", containerFactory = "kafkaListenerContainerFactory")
    public void atReportListenr(@Payload String message,
                                Acknowledgment acknowledgment) {
        Gson gson = new Gson();
        AtReportSaveRequestDto atReport = gson.fromJson(message, AtReportSaveRequestDto.class);
        String status = ApiCallCC.post(atReport.getEtc2(), message);
        log.info("At Topic Listner {}", status);
        if(status.equals("200")) {
            acknowledgment.acknowledge();
            log.info("at api call success");
        } else {
            throw new RuntimeException("at api server have some problem");
        }
    }

    @KafkaListener(topics = "${kafka.mt.report.topic.name}", groupId = "${kafka.mt.report.topic.group.name}", containerFactory = "kafkaListenerContainerFactory")
    public void mtReportlistenr(@Payload String message,
                             Acknowledgment acknowledgment) {
        Gson gson = new Gson();
        MtReportSaveRequestDto mtReport = gson.fromJson(message, MtReportSaveRequestDto.class);
        String status = ApiCallCC.post(mtReport.getEtc2(), message);
        log.info("Mt Topic Listner {}", status);
        if(status.equals("200")) {
//            registry.destroy();
//            registry.start();
            acknowledgment.acknowledge();
            log.info("mt api call success");
        } else {
            throw new RuntimeException("mt api server have some problem");
        }
    }

    private final  KafkaHealthIndicator kafkaHealthIndicator;

    @KafkaListener(topics = "${kafka.health.check.topic.name}", groupId = "${kafka.health.check.topic.group.name}", containerFactory = "kafkaListenerContainerFactory")
    public void hcChecklistenr(@Payload String message,
                             Acknowledgment acknowledgment) {
        log.info("Health Check Topic Listner {}", message);
        acknowledgment.acknowledge();
        kafkaHealthIndicator.setKafkaHealth("200");
    }

}
