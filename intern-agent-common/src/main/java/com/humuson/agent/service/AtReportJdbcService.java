/*
*   대량 메시지 DB insert시 성능 부하를 막기 위해 Batch Insert를 위한 Service
* */
package com.humuson.agent.service;

import com.humuson.agent.domain.repository.AtReportJdbcRepository;
import com.humuson.agent.dto.AtReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class AtReportJdbcService {

    private final AtReportJdbcRepository atReportJdbcRepository;

    @Transactional
    public void saveAll(List<AtReportSaveRequestDto> AtReportSaveRequestDto) {
        atReportJdbcRepository.saveAll(AtReportSaveRequestDto);
    }


}