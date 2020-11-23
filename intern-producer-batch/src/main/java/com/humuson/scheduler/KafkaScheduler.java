package com.humuson.scheduler;

import com.humuson.dto.AtMsgsLogListDto;
import com.humuson.dto.FtMsgsLogListDto;
import com.humuson.dto.MtMsgsLogListDto;
import com.humuson.service.AtMsgsLogService;
import com.humuson.service.FtMsgsLogService;
import com.humuson.service.MtMsgsLogService;
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
    private final AtMsgsLogService atMsgsLogService;
    private final FtMsgsLogService ftMsgsLogService;
    private final MtMsgsLogService mtMsgsLogService;

    // Agent DB 에서 imc_at_biz_msg_log 데이터 가져와!
    @Scheduled(cron = "*/10 * * * * *") // 10초에 한번 실행
    public void atLogSchedule() {
        log.info("=============at log 스케쥴러 작동중============= ");
        List<AtMsgsLogListDto> atMsgsLogList = atMsgsLogService.findAllByEtc1("0");
        if(atMsgsLogList.size() != 0) {
            log.info("=============at logetc1 0 인거 있다============= ");
            
            // 아래 두 서비스 배치 처리
            
            // kafka producer 생성하여 토픽에 전달
            producerSerivce.sendAtMsgsLogList(atMsgsLogList);
            // 토픽에 전달된 데이터 상태 변경
            atMsgsLogService.changeAllEtc1Status("0");
        } else {
            log.info("=============at logetc1 0 인거 없다============= ");
        }
    }

    @Scheduled(cron = "*/10 * * * * *") // 10초에 한번 실행
    public void ftLogSchedule() {
        log.info("=============ft log 스케쥴러 작동중============= ");
        List<FtMsgsLogListDto> ftMsgsLogList = ftMsgsLogService.findAllByEtc1("0");
        if(ftMsgsLogList.size() != 0) {
            log.info("=============ft log etc1 0 인거 있다============= ");

            // 아래 두 서비스 배치 처리

            // kafka producer 생성하여 토픽에 전달
            producerSerivce.sendFtMsgsLogList(ftMsgsLogList);
            // 토픽에 전달된 데이터 상태 변경
            ftMsgsLogService.changeAllEtc1Status("0");
        } else {
            log.info("=============ft log etc1 0 인거 없다============= ");
        }
    }

//    @Scheduled(cron = "*/10 * * * * *") // 10초에 한번 실행
//    public void mtLogSchedule() {
//        log.info("=============mt log 스케쥴러 작동중============= ");
//        List<MtMsgsLogListDto> mtMsgsLogList = mtMsgsLogService.findAllByEtc1("0");
//        if(mtMsgsLogList.size() != 0) {
//            log.info("=============mt log etc1 0 인거 있다============= ");
//
//            // 아래 두 서비스 배치 처리
//
//            // kafka producer 생성하여 토픽에 전달
//            producerSerivce.sendMtMsgsLogList(mtMsgsLogList);
//            // 토픽에 전달된 데이터 상태 변경
//            mtMsgsLogService.changeAllEtc1Status("0");
//        } else {
//            log.info("=============mt log etc1 0 인거 없다============= ");
//        }
//    }

}

