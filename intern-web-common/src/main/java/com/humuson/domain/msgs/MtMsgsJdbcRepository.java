package com.humuson.domain.msgs;

import java.util.List;

public interface MtMsgsJdbcRepository {
    void saveAll(List<MtMsgs> mtMsgs);
}
