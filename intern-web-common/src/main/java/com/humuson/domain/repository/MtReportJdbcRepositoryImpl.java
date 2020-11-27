package com.humuson.domain.repository;

import com.humuson.domain.report.MtReport;
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
public class MtReportJdbcRepositoryImpl implements MtReportJdbcRepository{

    private final JdbcTemplate jdbcTemplate;

    private int batchSize = 500;

    @Override
    public void saveAll(List<MtReport> mtReportList) {
        int batchCount = 0;
        List<MtReport> subItems = new ArrayList<>();
        for (int i = 0; i < mtReportList.size(); i++) {
            subItems.add(mtReportList.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchInsert(batchSize, batchCount, subItems);
            }
        }
        if (!subItems.isEmpty()) {
            batchCount = batchInsert(batchSize, batchCount, subItems);
        }
        log.info("DB Save all batchCount: " + batchCount);

    }

    private int batchInsert(int batchSize, int batchCount, List<MtReport> mtReportList) {

        jdbcTemplate.batchUpdate("INSERT INTO imc_at (`status`,`priority`,`reserved_date`,`sender_key`,`phone_number`, `template_code`,`message`," +
                        "`request_uid``request_date`,`response_date`,`response_code`,`report_type`,`report_date`,`report_code`,`arrival_date`,`etc1`,`etc2`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    // TODO 직접 insert 하기 때문에 default값 재설정 필요
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, mtReportList.get(i).getStatus());
                        ps.setString(2, mtReportList.get(i).getPriority());
                        ps.setString(3, mtReportList.get(i).getReserved_date());
                        ps.setString(4, mtReportList.get(i).getAd_flag());
                        ps.setString(5, mtReportList.get(i).getPhone_number());
                        ps.setString(6, mtReportList.get(i).getMt_type());
                        ps.setString(7, mtReportList.get(i).getMessage());
                        ps.setString(8, mtReportList.get(i).getRequest_uid());
                        ps.setString(9, mtReportList.get(i).getRequest_date());
                        ps.setString(10, mtReportList.get(i).getReserved_date());
                        ps.setString(11, mtReportList.get(i).getResponse_code());
                        ps.setString(12, mtReportList.get(i).getReport_type());
                        ps.setString(13, mtReportList.get(i).getReport_date());
                        ps.setString(14, mtReportList.get(i).getReport_code());
                        ps.setString(15, mtReportList.get(i).getArrival_date());
                        ps.setString(16, mtReportList.get(i).getEtc1());
                        ps.setString(17, mtReportList.get(i).getEtc2());
                    }
                    @Override
                    public int getBatchSize() {
                        return mtReportList.size();
                    }
                });
        mtReportList.clear();
        batchCount++;
        return batchCount;
    }

}
