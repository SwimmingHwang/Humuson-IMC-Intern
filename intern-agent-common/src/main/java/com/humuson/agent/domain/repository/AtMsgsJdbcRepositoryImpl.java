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
public class AtMsgsJdbcRepositoryImpl implements AtMsgsJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    private int batchSize = 500;

    @Override
    public void saveAll(List<AtMsgsSaveRequestDto> atMsgsSaveRequestDtos) {
        int batchCount = 0;
        List<AtMsgsSaveRequestDto> subItems = new ArrayList<>();
        for (int i = 0; i < atMsgsSaveRequestDtos.size(); i++) {
            subItems.add(atMsgsSaveRequestDtos.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchInsert(batchSize, batchCount, subItems);
            }
        }
        if (!subItems.isEmpty()) {
            batchCount = batchInsert(batchSize, batchCount, subItems);
        }
        log.info("batchCount: " + batchCount);
    }

    private int batchInsert(int batchSize, int batchCount, List<AtMsgsSaveRequestDto> atMsgsSaveRequestDtos) {
        jdbcTemplate.batchUpdate("INSERT INTO imc_at (`reserved_date`,`template_code`,`message`, `phone_number`, `etc1`, `etc2`) VALUES (?,?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, atMsgsSaveRequestDtos.get(i).getReservedDate());
                        ps.setString(2, atMsgsSaveRequestDtos.get(i).getTemplateCode());
                        ps.setString(3, atMsgsSaveRequestDtos.get(i).getMsg());
                        ps.setString(4, atMsgsSaveRequestDtos.get(i).getPhoneNumber());
                        ps.setString(5, atMsgsSaveRequestDtos.get(i).getEtc1());
                        ps.setString(6, atMsgsSaveRequestDtos.get(i).getEtc2());
                    }
                    @Override
                    public int getBatchSize() {
                        return atMsgsSaveRequestDtos.size();
                    }

                });
        atMsgsSaveRequestDtos.clear();
        batchCount++;
        return batchCount;
    }


}
