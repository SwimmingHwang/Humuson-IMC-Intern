package com.humuson.agent.domain.repository;

import com.humuson.agent.dto.AtMsgsSaveRequestDto;

import java.util.List;

public interface AtMsgsJdbcRepository {
    void saveAll(List<AtMsgsSaveRequestDto> atMsgsSaveRequestDtos);
}
