package com.humuson.service;
import com.humuson.domain.msgs.AtMsgsLog;
import com.humuson.domain.msgs.AtMsgsLogRepository;
import com.humuson.dto.AtMsgsLogListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AtMsgsLogService {

    private final AtMsgsLogRepository atMsgsLogRepository;

    // etc1가 0 -> 옮겨지지 않은 데이터만 뽑음
    @Transactional(readOnly = true)
    public List<AtMsgsLogListDto> findAllByEtc1(String status) {
        return atMsgsLogRepository.findAll().stream()
                .filter(atMsgsLog -> atMsgsLog.getEtc1().equals("0"))
                .map(AtMsgsLogListDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AtMsgsLog> findAll() {
        return atMsgsLogRepository.findAll();
    }

    @Transactional
    public void saveEtc1Status(Long id) {
        AtMsgsLog at = atMsgsLogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        at.setEtc1Status("1");
        atMsgsLogRepository.save(at);
    }

    @Transactional
    public void changeAllEtc1Status(String status) {
        atMsgsLogRepository.findAllByEtc1(status).stream()
                .forEach(at -> {
                    at.setEtc1Status("1");
                    atMsgsLogRepository.save(at);
                });
    }

//
//    @Transactional
//    public void updateEtc1Status(List<AtMsgsLog> atMsgsLogList) {
//        atMsgsLogList.stream().forEach(atMsgsLog -> atMsgsLog.setEtc1Status("1"));
//        atMsgsLogRepository.saveAll(atMsgsLogList);
//    }
}
