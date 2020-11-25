package com.humuson.agent.domain.repository;

import com.humuson.agent.dto.AtReportSaveRequestDto;

import java.util.List;

public interface AtReportJdbcRepository {
    void saveAll(List<AtReportSaveRequestDto> atReportSaveRequestDtos);
}
