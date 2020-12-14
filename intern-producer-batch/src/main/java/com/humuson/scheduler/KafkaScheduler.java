package com.humuson.scheduler;

import com.humuson.agent.dto.AtReportSaveRequestDto;
import com.humuson.agent.dto.MtReportSaveRequestDto;
import com.humuson.agent.service.AtReportService;
import com.humuson.agent.service.MtReportService;
import com.humuson.utility.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaScheduler {

    private final Producer producer;
    private final AtReportService atReportService;
    private final MtReportService mtReportService;

    // Agent DB 에서 imc_at_biz_msg_log 데이터 가져와!
    @Scheduled(initialDelay = 1000, fixedDelay = 10000) // 스케쥴 끝나고 10초 뒤 재실행
    public void atReportSchedule() {
        List<AtReportSaveRequestDto> atReportList = atReportService.findAllByEtc1("0");
        if(atReportList.size() != 0) {
            log.info("=============at report etc1 0 인거 있다============= ");
            // kafka producer 생성하여 토픽에 전달
            producer.sendAtReportList(atReportList);
            // 토픽에 전달된 데이터 상태 변경
            atReportService.changeAllEtc1Status("0");
        }
    }


    @Scheduled(initialDelay = 1000, fixedDelay = 10000) // 10초에 한번 실행
    public void mtReportSchedule() {
        List<MtReportSaveRequestDto> mtReportList = mtReportService.findAllByEtc1("0");
        if(mtReportList.size() != 0) {
            log.info("=============mt report etc1 0 인거 있다============= ");
            log.info(mtReportList.toString());
            // 아래 두 서비스 배치 처리
            // kafka producer 생성하여 토픽에 전달
            producer.sendMtReportList(mtReportList);
            // 토픽에 전달된 데이터 상태 변경
            mtReportService.changeAllEtc1Status("0");
        }
    }

}

