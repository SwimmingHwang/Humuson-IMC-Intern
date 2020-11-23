package com.humuson.domain.repository;

import com.humuson.domain.entity.AtMsgsLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtMsgsLogRepository extends JpaRepository<AtMsgsLog, Long> {
    List<AtMsgsLog> findAllByEtc1(String status);
}
