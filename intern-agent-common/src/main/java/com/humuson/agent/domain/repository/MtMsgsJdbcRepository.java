package com.humuson.agent.domain.repository;

import com.humuson.agent.domain.entity.MtMsgs;

import java.util.List;

public interface MtMsgsJdbcRepository {
    void saveAll(List<MtMsgs> mtMsgs);
}
