package com.humuson.domain.repository;

import com.humuson.domain.msgs.AtMsgs;

import java.util.List;

public interface AtMsgsJdbcRepository{
    void saveAll(List<AtMsgs> atMsgs);
    void updateAllStatus(List<Integer> idList);
}
