/*
*   대량 메시지 DB insert시 성능 부하를 막기 위해 Batch Insert를 위한 Service
* */
package com.humuson.service;

import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.msgs.AtMsgsJdbcRepository;
import com.humuson.domain.repository.CustomerRepository;
import com.humuson.dto.at.MultiAtMsgsSaveListRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class AtMsgsJdbcService {

    private final AtMsgsJdbcRepository atMsgsJdbcRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public void saveAll(List<AtMsgs> atMsgs) {
        atMsgsJdbcRepository.saveAll(atMsgs);
    }

    @Transactional
    public void saveAllList(MultiAtMsgsSaveListRequestDto requestDto) {
        // requestDto 를 AtMsgs 리스트로 변환
        List<AtMsgs> atMsgs = requestDto.toEntity(customerRepository.findAll());
        atMsgsJdbcRepository.saveAll(atMsgs);
    }
}