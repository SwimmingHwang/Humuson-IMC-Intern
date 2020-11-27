package com.humuson.service;

import com.humuson.domain.report.MtReport;
import com.humuson.domain.repository.MtReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MtReportService {

    private final MtReportRepository mtReportRepository;

    @Transactional
    public void save(MtReport atReport) {
        mtReportRepository.save(atReport);
    }
}
