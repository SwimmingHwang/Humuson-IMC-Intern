package com.humuson.domain.repository;

import com.humuson.domain.report.MtReport;

import java.util.List;

public interface MtReportJdbcRepository {
    void saveAll(List<MtReport> mtReportList);
}
