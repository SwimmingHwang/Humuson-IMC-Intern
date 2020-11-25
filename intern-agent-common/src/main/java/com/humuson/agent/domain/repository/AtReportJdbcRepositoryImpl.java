package com.humuson.agent.domain.repository;

import com.humuson.agent.dto.AtMsgsSaveRequestDto;
import com.humuson.agent.dto.AtReportSaveRequestDto;
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
public class AtReportJdbcRepositoryImpl implements AtReportJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    private int batchSize = 500;

    @Override
    public void saveAll(List<AtReportSaveRequestDto> atReportSaveRequestDtos) {
        int batchCount = 0;
        List<AtReportSaveRequestDto> subItems = new ArrayList<>();
        for (int i = 0; i < atReportSaveRequestDtos.size(); i++) {
            subItems.add(atReportSaveRequestDtos.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchInsert(batchSize, batchCount, subItems);
            }
        }
        if (!subItems.isEmpty()) {
            batchCount = batchInsert(batchSize, batchCount, subItems);
        }
        log.info("batchCount: " + batchCount);
    }

    private int batchInsert(int batchSize, int batchCount, List<AtReportSaveRequestDto> atReportSaveRequestDtos) {

        jdbcTemplate.batchUpdate("INSERT INTO imc_at_biz_msg (`reserved_date`,`sender_key`,`phone_number`, `template_code`,`message`," +
                        "`etc1`,`etc2`) VALUES (?,?,?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, atReportSaveRequestDtos.get(i).getReserved_date());
                        ps.setString(2, atReportSaveRequestDtos.get(i).getSender_key());
                        ps.setString(3, atReportSaveRequestDtos.get(i).getPhone_number());
                        ps.setString(4, atReportSaveRequestDtos.get(i).getTemplate_code());
                        ps.setString(5, atReportSaveRequestDtos.get(i).getMessage());
                        ps.setString(6, atReportSaveRequestDtos.get(i).getEtc1());
                        ps.setString(7, atReportSaveRequestDtos.get(i).getEtc2());
                    }
                    @Override
                    public int getBatchSize() {
                        return atReportSaveRequestDtos.size();
                    }

                });
        atReportSaveRequestDtos.clear();
        batchCount++;
        return batchCount;
    }

}
