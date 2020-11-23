package com.humuson.agent.service;

import com.humuson.agent.domain.repository.FtMsgsRepository;
import com.humuson.agent.dto.FtMsgsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class FtMsgsService {
    private final FtMsgsRepository ftMsgsRepository;

    public void save(FtMsgsSaveRequestDto ftMsgstDto) {
        ftMsgsRepository.save(ftMsgstDto.toEntity());
    }
}