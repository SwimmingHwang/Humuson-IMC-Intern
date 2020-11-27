package com.humuson.service;

import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.repository.AtMsgsJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class AtReportJdbcService {

    private final AtMsgsJdbcRepository atMsgsJdbcRepository;

    @Transactional
    public void saveAll(List<AtMsgs> atMsgs) {
        atMsgsJdbcRepository.saveAll(atMsgs);
    }
}
