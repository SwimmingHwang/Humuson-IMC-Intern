package com.humuson.domain.report;

import java.util.List;

public interface MtReportJdbcRepository {
    void saveAll(List<MtReport> mtReportList);
}
