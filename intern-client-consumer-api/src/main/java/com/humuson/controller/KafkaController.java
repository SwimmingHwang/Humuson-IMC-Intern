package com.humuson.controller;

import com.humuson.domain.msgslog.AtMsgsLog;
import com.humuson.domain.msgslog.AtMsgsLogRepository;
import com.humuson.dto.AtMsgsLogDto;
import com.humuson.dto.AtMsgsLogListDto;
import com.humuson.service.AtMsgsLogService;
import com.humuson.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class KafkaController {

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // KafkaConfiguration에서 작성한 Bean 주입.
    private final KafkaTemplate kafkaTemplate;

    @GetMapping("/get")
    public String getData(@RequestParam(value = "message", required = true, defaultValue = "") String message ){
        // 현재 시간
        LocalDateTime date = LocalDateTime.now();
        String dateStr = date.format(fmt);

        // atMsgsLog 토픽에 현재 시간 + message를 produce 한다.
        kafkaTemplate.send("atMsgsLog", dateStr + "   " + message);
        return "kafkaTemplate.send >>  " + message ;
    }

    private final AtMsgsLogService atMsgsLogService;
    private final ProducerService producerSerivce;

    // Agent DB 에서 imc_at_biz_msg_log 데이터 가져와!
    @Scheduled(cron = "*/10 * * * * *") // 10초에 한번 실행
    public void atLogSchedule() {
        log.info("=============스케쥴러 작동중============= ");
        List<AtMsgsLogListDto> atMsgsLogList = atMsgsLogService.findAllByEtc1("0");
        if(atMsgsLogList.size() != 0) {
            log.info("=============etc1 0 인거 있다============= ");
            // kafka producer 생성하여 토픽에 전달
            producerSerivce.sendatMsgsLogList(atMsgsLogList);
            atMsgsLogService.changeAllEtc1Status("0");
//            atMsgsLogList.stream().forEach(at -> {
//                atMsgsLogService.saveEtc1Status(at.getId());
//            });
        } else {
            log.info("=============etc1 0 인거 없다다다다============= ");
        }
    }

}

