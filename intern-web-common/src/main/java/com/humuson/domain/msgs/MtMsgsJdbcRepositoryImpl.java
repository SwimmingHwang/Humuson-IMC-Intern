package com.humuson.domain.msgs;

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
public class MtMsgsJdbcRepositoryImpl implements MtMsgsJdbcRepository{

    private final JdbcTemplate jdbcTemplate;

    private int batchSize = 500;

    @Override
    public void saveAll(List<MtMsgs> mtMsgs) {
        int batchCount = 0;
        List<MtMsgs> subItems = new ArrayList<>();
        for (int i = 0; i < mtMsgs.size(); i++) {
            subItems.add(mtMsgs.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchInsert(batchSize, batchCount, subItems);
            }
        }
        if (!subItems.isEmpty()) {
            batchCount = batchInsert(batchSize, batchCount, subItems);
        }
        log.info("DB Save all batchCount: " + batchCount);

    }

    private int batchInsert(int batchSize, int batchCount, List<MtMsgs> mtMsgs) {

        jdbcTemplate.batchUpdate("INSERT INTO imc_mt (`status`,`priority`,`reserved_date`,`callback`, " +
                        "`mt_type`, `ad_flag`,`message`, `phone_number`,`etc1`,`etc2`) VALUES (?,?,?,?,?,?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
            // TODO 직접 insert 하기 때문에 default값 재설정 필요
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, mtMsgs.get(i).getStatus());
                        ps.setString(2, mtMsgs.get(i).getPriority());
                        ps.setString(3, mtMsgs.get(i).getReservedDate());
                        ps.setString(4, mtMsgs.get(i).getCallback());
                        ps.setString(5, mtMsgs.get(i).getMtType());
                        ps.setString(6, mtMsgs.get(i).getAdFlag());
                        ps.setString(7, mtMsgs.get(i).getMsg());
                        ps.setString(8, mtMsgs.get(i).getPhoneNumber());
                        ps.setString(9, mtMsgs.get(i).getEtc1());
                        ps.setString(10, mtMsgs.get(i).getEtc2());
                    }
                    @Override
                    public int getBatchSize() {
                        return mtMsgs.size();
                    }
                });
        mtMsgs.clear();
        batchCount++;
        return batchCount;
    }


}
