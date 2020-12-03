/*
*   대량 메시지 DB insert시 성능 부하를 막기 위해 Batch Insert를 위한 Service
* */
package com.humuson.agent.service;

import com.humuson.agent.domain.entity.AtMsgs;
import com.humuson.agent.domain.repository.AtMsgsJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class AtMsgsJdbcService {

    private final AtMsgsJdbcRepository atMsgsJdbcRepository;

    @Transactional
    public void saveAll(List<AtMsgs> atMsgs) {
        atMsgsJdbcRepository.saveAll(atMsgs);
    }


}