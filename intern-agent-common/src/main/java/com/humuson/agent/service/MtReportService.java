package com.humuson.agent.service;

import com.humuson.agent.domain.entity.MtReport;
import com.humuson.agent.domain.repository.MtReportRepository;
import com.humuson.agent.dto.AtReportSaveRequestDto;
import com.humuson.agent.dto.MtReportListDto;
import com.humuson.agent.dto.MtReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MtReportService {
    private final MtReportRepository mtReportRepository;

    // etc1가 0 -> 옮겨지지 않은 데이터만 뽑음
    @Transactional(readOnly = true)
    public List<MtReportSaveRequestDto> findAllByEtc1(String status) {
        return mtReportRepository.findAll().stream()
                .filter(mtReport -> mtReport.getEtc1().equals(status))
                .map(MtReportSaveRequestDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MtReport> findAll() {
        return mtReportRepository.findAll();
    }

    @Transactional
    public void saveEtc1Status(Long id) {
        MtReport at = mtReportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        at.setEtc1Status("1");
        mtReportRepository.save(at);
    }

    @Transactional
    public void changeAllEtc1Status(String status) {
        mtReportRepository.findAllByEtc1(status).stream()
                .forEach(at -> {
                    at.setEtc1Status("1");
                    mtReportRepository.save(at);
                });
    }
}