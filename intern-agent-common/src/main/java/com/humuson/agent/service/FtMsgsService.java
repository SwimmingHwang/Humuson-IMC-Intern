package com.humuson.agent.service;

import com.humuson.agent.domain.repository.FtMsgsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class FtMsgsService {
    private final FtMsgsRepository ftMsgsRepository;

}