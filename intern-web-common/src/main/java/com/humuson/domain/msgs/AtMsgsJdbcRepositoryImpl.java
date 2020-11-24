package com.humuson.domain.msgs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
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
public class AtMsgsJdbcRepositoryImpl implements AtMsgsJdbcRepository{

    private final JdbcTemplate jdbcTemplate;

    private int batchSize = 500;

    @Override
    public void saveAll(List<AtMsgs> atMsgs) {
        int batchCount = 0;
        List<AtMsgs> subItems = new ArrayList<>();
        for (int i = 0; i < atMsgs.size(); i++) {
            subItems.add(atMsgs.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchInsert(batchSize, batchCount, subItems);
            }
        }
        if (!subItems.isEmpty()) {
            batchCount = batchInsert(batchSize, batchCount, subItems);
        }
        log.info("batchCount: " + batchCount);

    }

    private int batchInsert(int batchSize, int batchCount, List<AtMsgs> atMsgs) {

        jdbcTemplate.batchUpdate("INSERT INTO imc_at (`status`,`priority`,`reserved_date`,`sender_key`, " +
                        "`template_code`, `etc1`,`message`, `phone_number`) VALUES (?, ?,?,?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
            // TODO 직접 insert 하기 때문에 default값 재설정 필요
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, atMsgs.get(i).getStatus());
                        ps.setString(2, atMsgs.get(i).getPriority());
                        ps.setString(3, atMsgs.get(i).getReservedDate());
                        ps.setString(4, atMsgs.get(i).getSenderKey());
                        ps.setString(5, atMsgs.get(i).getTemplateCode());
                        ps.setString(6, atMsgs.get(i).getEtc1());
                        ps.setString(7, atMsgs.get(i).getMsg());
                        ps.setString(8, atMsgs.get(i).getPhoneNumber());
                    }
                    @Override
                    public int getBatchSize() {
                        return atMsgs.size();
                    }
                });
        atMsgs.clear();
        batchCount++;
        return batchCount;
    }

    @Override
    public void updateAllStatus(List<Integer> idList) {
        int batchCount = 0;
        List<Integer> subItems = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            subItems.add(idList.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchStatusUpdate(batchSize, batchCount, subItems);
            }
        }
        if (!subItems.isEmpty()) {
            batchCount = batchStatusUpdate(batchSize, batchCount, subItems);
        }
        log.info("batchCount: " + batchCount);
    }

    private int batchStatusUpdate(int batchSize, int batchCount, List<Integer> idList) {
        jdbcTemplate.batchUpdate("UPDATE imc_at SET `status`=`3` WHERE STATUS=`2` AND ETC2=?",
                new BatchPreparedStatementSetter() {
                    // TODO 직접 insert 하기 때문에 default값 재설정 필요
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, idList.get(i));
                    }
                    @Override
                    public int getBatchSize() {
                        return idList.size();
                    }
                });
        idList.clear();
        batchCount++;
        return batchCount;
    }

}
