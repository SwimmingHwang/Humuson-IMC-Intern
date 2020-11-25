package com.humuson.scheduler;

import com.humuson.agent.dto.AtReportDto;
import com.humuson.agent.dto.AtReportSaveRequestDto;
import com.humuson.agent.service.AtReportService;
import com.humuson.agent.service.FtReportService;
import com.humuson.agent.service.MtReportService;
import com.humuson.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaScheduler {

    private final ProducerService producerSerivce;
    private final AtReportService atReportService;
    private final FtReportService ftMsgsLogService;
    private final MtReportService mtMsgsLogService;

    // Agent DB 에서 imc_at_biz_msg_log 데이터 가져와!
    @Scheduled(cron = "*/10 * * * * *") // 10초에 한번 실행
    public void atReportSchedule() {
        log.info("=============at report 스케쥴러 작동중============= ");
        List<AtReportSaveRequestDto> atReportList = atReportService.findAllByEtc1("0");
        if(atReportList.size() != 0) {
            log.info("=============at report etc1 0 인거 있다============= ");

            // kafka producer 생성하여 토픽에 전달
            producerSerivce.sendAtReportList(atReportList);
            // 토픽에 전달된 데이터 상태 변경
            atReportService.changeAllEtc1Status("0");
        } else {
            log.info("=============at report etc1 0 인거 없다============= ");
        }
    }

//    @Scheduled(cron = "*/10 * * * * *") // 10초에 한번 실행
//    public void ftLogSchedule() {
//        log.info("=============ft report 스케쥴러 작동중============= ");
//        List<FtReportListDto> ftReportList = ftMsgsLogService.findAllByEtc1("0");
//        if(ftReportList.size() != 0) {
//            log.info("=============ft report etc1 0 인거 있다============= ");
//
//            // 아래 두 서비스 배치 처리
//
//            // kafka producer 생성하여 토픽에 전달
//            producerSerivce.sendFtReportList(ftReportList);
//            // 토픽에 전달된 데이터 상태 변경
//            ftMsgsLogService.changeAllEtc1Status("0");
//        } else {
//            log.info("=============ft report etc1 0 인거 없다============= ");
//        }
//    }

//    @Scheduled(cron = "*/10 * * * * *") // 10초에 한번 실행
//    public void mtLogSchedule() {
//        log.info("=============mt report 스케쥴러 작동중============= ");
//        List<MtReportListDto> mtMsgsLogList = mtMsgsLogService.findAllByEtc1("0");
//        if(mtMsgsLogList.size() != 0) {
//            log.info("=============mt report etc1 0 인거 있다============= ");
//
//            // 아래 두 서비스 배치 처리
//
//            // kafka producer 생성하여 토픽에 전달
//            producerSerivce.sendMtReportList(mtReportList);
//            // 토픽에 전달된 데이터 상태 변경
//            mtReportService.changeAllEtc1Status("0");
//        } else {
//            log.info("=============mt report etc1 0 인거 없다============= ");
//        }
//    }

}

