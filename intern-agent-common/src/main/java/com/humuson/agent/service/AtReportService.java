package com.humuson.agent.service;

import com.humuson.agent.domain.entity.AtReport;
import com.humuson.agent.domain.repository.AtReportRepository;
import com.humuson.agent.dto.AtReportDto;
import com.humuson.agent.dto.AtReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AtReportService {

    private final AtReportRepository atReportRepository;

    // etc1가 0 -> 옮겨지지 않은 데이터만 뽑음
    @Transactional(readOnly = true)
    public List<AtReportSaveRequestDto> findAllByEtc1(String status) {
        return atReportRepository.findAll().stream()
                .filter(atReport -> atReport.getEtc1().equals(status))
                .map(AtReportSaveRequestDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AtReport> findAll() {
        return atReportRepository.findAll();
    }

    @Transactional
    public void saveEtc1Status(Long id) {
        AtReport at = atReportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        at.setEtc1Status("1");
        atReportRepository.save(at);
    }

    @Transactional
    public void changeAllEtc1Status(String status) {
        atReportRepository.findAllByEtc1(status).stream()
                .forEach(at -> {
                    at.setEtc1Status("1");
                    atReportRepository.save(at);
                });
    }

}
