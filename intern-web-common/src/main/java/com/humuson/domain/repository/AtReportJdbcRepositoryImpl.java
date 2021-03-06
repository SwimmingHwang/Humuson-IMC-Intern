package com.humuson.domain.repository;

import com.humuson.domain.report.AtReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AtReportJdbcRepositoryImpl implements AtReportJdbcRepository{

    private final JdbcTemplate jdbcTemplate;

    private int batchSize = 500;

    @Override
    public void saveAll(List<AtReport> atReportList) {
        int batchCount = 0;
        List<AtReport> subItems = new ArrayList<>();
        for (int i = 0; i < atReportList.size(); i++) {
            subItems.add(atReportList.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchInsert(batchSize, batchCount, subItems);
            }
        }
        if (!subItems.isEmpty()) {
            batchCount = batchInsert(batchSize, batchCount, subItems);
        }
        log.info("At DB Save all batchCount: " + batchCount);

    }

    private int batchInsert(int batchSize, int batchCount, List<AtReport> atReportList) {

        jdbcTemplate.batchUpdate("INSERT INTO imc_at_report (`reserved_date`,`phone_number`," +
                        "`request_uid``request_date`,`response_date`,`response_code`, `report_date`,`report_code`,`arrival_date`) VALUES (?,?,?,?,?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    // TODO 직접 insert 하기 때문에 default값 재설정 필요
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, atReportList.get(i).getReserved_date());
                        ps.setString(2, atReportList.get(i).getPhone_number());
                        ps.setString(3, atReportList.get(i).getRequest_uid());
                        ps.setString(4, atReportList.get(i).getRequest_date());
                        ps.setString(5, atReportList.get(i).getResponse_date());
                        ps.setString(6, atReportList.get(i).getResponse_code());
                        ps.setString(7, atReportList.get(i).getReport_date());
                        ps.setString(8, atReportList.get(i).getReport_code());
                        ps.setString(9, atReportList.get(i).getArrival_date());
                    }
                    @Override
                    public int getBatchSize() {
                        return atReportList.size();
                    }
                });
        atReportList.clear();
        batchCount++;
        return batchCount;
    }

}
