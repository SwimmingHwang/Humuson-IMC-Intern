package com.humuson.domain.repository;

import com.humuson.domain.report.AtReport;

import java.util.List;

public interface AtReportJdbcRepository {
    void saveAll(List<AtReport> atReportList);
}
