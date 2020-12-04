package com.humuson.service;

import com.humuson.domain.report.AtReport;
import com.humuson.domain.repository.AtReportRepository;
import com.humuson.dto.report.AtReportListDashboardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AtReportService {

    private final AtReportRepository atReportRepository;

    @Transactional
    public void save(AtReport atReport) {
        atReportRepository.save(atReport);
    }

    @Transactional
    public List<AtReport> findAll() {
        List<AtReport> atReportList = atReportRepository.findAll();
        return atReportList;
    }

    @Transactional
    public List<AtReportListDashboardResponseDto> findInfoList() {
        return atReportRepository.findAll().stream()
                .map(AtReportListDashboardResponseDto::new)
                .collect(Collectors.toList());
    }
}
