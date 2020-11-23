package com.humuson.agent.service;

import com.humuson.agent.domain.repository.MtMsgsRepository;
import com.humuson.agent.dto.MtMsgsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class MtMsgsService {
    private final MtMsgsRepository mtMsgsRepository;

    @Transactional
    public Integer save(MtMsgsSaveRequestDto requestDto) {
        return mtMsgsRepository.save(requestDto.toEntity()).getId();
    }
}