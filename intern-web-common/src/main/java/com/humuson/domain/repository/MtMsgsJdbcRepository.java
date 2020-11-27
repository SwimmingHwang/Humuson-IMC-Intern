package com.humuson.domain.repository;

import com.humuson.domain.msgs.MtMsgs;

import java.util.List;

public interface MtMsgsJdbcRepository {
    void saveAll(List<MtMsgs> mtMsgs);
}
