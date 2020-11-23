package com.humuson.agent.service;

import com.humuson.agent.domain.repository.AtMsgsRepository;
import com.humuson.agent.dto.AtMsgsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AtMsgsService {
    private final AtMsgsRepository atMsgsRepository;

    public void save(AtMsgsSaveRequestDto atMsgstDto) {
        atMsgsRepository.save(atMsgstDto.toEntity());
    }



}
