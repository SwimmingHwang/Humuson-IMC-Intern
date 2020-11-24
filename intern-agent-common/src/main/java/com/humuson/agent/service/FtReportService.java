package com.humuson.agent.service;

import com.humuson.agent.domain.entity.FtReport;
import com.humuson.agent.domain.repository.FtReportRepository;
import com.humuson.agent.dto.FtReportListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor// final이 선언된 모든 필드를 인자값으로하는 생성자를 생성해줌.
@Service
public class FtReportService {
    private final FtReportRepository ftMsgsLogRepository;

    @Transactional(readOnly = true)
    public List<FtReportListDto> findAllByEtc1(String status) {
        return ftMsgsLogRepository.findAll().stream()
                .filter(ftMsgsLog -> ftMsgsLog.getEtc1().equals(status))
                .map(FtReportListDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FtReport> findAll() {
        return ftMsgsLogRepository.findAll();
    }

    @Transactional
    public void saveEtc1Status(Long id) {
        FtReport at = ftMsgsLogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        at.setEtc1Status("1");
        ftMsgsLogRepository.save(at);
    }

    @Transactional
    public void changeAllEtc1Status(String status) {
        ftMsgsLogRepository.findAllByEtc1(status).stream()
                .forEach(at -> {
                    at.setEtc1Status("1");
                    ftMsgsLogRepository.save(at);
                });
    }
}