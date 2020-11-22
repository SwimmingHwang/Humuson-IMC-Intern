package com.humuson.domain.msgs;

import java.util.List;

public interface AtMsgsJdbcRepository{
    void saveAll(List<AtMsgs> atMsgs);
}
