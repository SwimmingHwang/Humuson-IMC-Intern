package com.humuson.agent.service;

import com.humuson.agent.domain.entity.MtReport;
import com.humuson.agent.domain.repository.MtReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MtReportService {
    private final MtReportRepository mtMsgsLogRepository;

//    @Transactional(readOnly = true)
//    public List<MtMsgsLogListDto> findAllByEtc1(String status) {
//        return mtMsgsLogRepository.findAll().stream()
//                .filter(mtMsgsLog -> mtMsgsLog.getEtc1().equals(status))
//                .map(MtMsgsLogListDto::new)
//                .collect(Collectors.toList());
//    }

    @Transactional(readOnly = true)
    public List<MtReport> findAll() {
        return mtMsgsLogRepository.findAll();
    }

    @Transactional
    public void saveEtc1Status(Long id) {
        MtReport at = mtMsgsLogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        at.setEtc1Status("1");
        mtMsgsLogRepository.save(at);
    }

    @Transactional
    public void changeAllEtc1Status(String status) {
        mtMsgsLogRepository.findAllByEtc1(status).stream()
                .forEach(at -> {
                    at.setEtc1Status("1");
                    mtMsgsLogRepository.save(at);
                });
    }
}