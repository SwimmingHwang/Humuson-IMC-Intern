package com.humuson.service;

import com.humuson.domain.report.MtReport;
import com.humuson.domain.repository.MtReportRepository;
import com.humuson.dto.report.MtReportListDashboardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MtReportService {

    private final MtReportRepository mtReportRepository;

    @Transactional
    public void save(MtReport atReport) {
        mtReportRepository.save(atReport);
    }

    @Transactional
    public List<MtReport> findAll() {
        List<MtReport> mtReportList = mtReportRepository.findAll();
        return mtReportList;
    }

    @Transactional
    public List<MtReportListDashboardResponseDto> findInfoList() {
        return mtReportRepository.findAll().stream()
                .map(MtReportListDashboardResponseDto::new)
                .collect(Collectors.toList());
    }
}
