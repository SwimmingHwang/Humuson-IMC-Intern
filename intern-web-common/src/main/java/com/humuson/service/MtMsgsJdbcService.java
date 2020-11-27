/*
*   대량 메시지 DB insert시 성능 부하를 막기 위해 Batch Insert를 위한 Service
* */
package com.humuson.service;

import com.humuson.domain.msgs.MtMsgs;
import com.humuson.domain.repository.MtMsgsJdbcRepository;
import com.humuson.domain.repository.CustomerRepository;
import com.humuson.dto.mt.MultiMtMsgsSaveListRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class MtMsgsJdbcService {

    private final MtMsgsJdbcRepository mtMsgsJdbcRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public void saveAll(List<MtMsgs> mtMsgs) {
        mtMsgsJdbcRepository.saveAll(mtMsgs);
    }

    @Transactional
    public void saveAllList(MultiMtMsgsSaveListRequestDto requestDto) {
        // requestDto 를 AtMsgs 리스트로 변환
        List<MtMsgs> mtMsgs = requestDto.toEntity(customerRepository.findAll());
        mtMsgsJdbcRepository.saveAll(mtMsgs);
    }
}