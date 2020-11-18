package com.humuson.controller;

import com.humuson.dto.AtMsgsLogListDto;
import com.humuson.service.AtMsgsLogService;
import com.humuson.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class KafkaController {

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
            // 토픽에 전달된 데이터 상태 변경
            atMsgsLogService.changeAllEtc1Status("0");
        } else {
            log.info("=============etc1 0 인거 없다============= ");
        }
    }

}

