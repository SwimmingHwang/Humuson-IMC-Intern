package com.humuson.agent.domain.repository;

import com.humuson.agent.domain.entity.AtMsgs;

import java.util.List;

public interface AtMsgsJdbcRepository{
    void saveAll(List<AtMsgs> atMsgs);
}
