package com.humuson.domain.report;

import java.util.List;

public interface AtReportJdbcRepository {
    void saveAll(List<AtReport> atReportList);
}
