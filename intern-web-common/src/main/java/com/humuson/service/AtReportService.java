package com.humuson.service;

import com.humuson.domain.report.AtReport;
import com.humuson.domain.report.AtReportRepository;
import com.humuson.dto.report.AtReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AtReportService {

    private final AtReportRepository atReportRepository;

    @Transactional
    public Long save(AtReport atReport) {
        return atReportRepository.save(atReport).getId(); // insert/update 쿼리 실행
    }
}
