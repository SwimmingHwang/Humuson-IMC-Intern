package com.humuson.domain.msgs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtMsgsLogRepository extends JpaRepository<AtMsgsLog, Long> {
    List<AtMsgsLog> findAllByEtc1(String status);
}
