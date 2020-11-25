package com.humuson.agent.service;

import com.humuson.agent.domain.repository.AtMsgsRepository;
import com.humuson.agent.domain.repository.AtReportRepository;
import com.humuson.agent.dto.AtReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AtMsgsService {
    private final AtReportRepository atMsgsRepository;

    public void save(AtReportSaveRequestDto atMsgstDto) {
        atMsgsRepository.save(atMsgstDto.toEntity());
    }



}
