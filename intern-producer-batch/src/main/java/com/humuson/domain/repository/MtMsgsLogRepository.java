package com.humuson.domain.repository;

import com.humuson.domain.entity.MtMsgsLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MtMsgsLogRepository extends JpaRepository<MtMsgsLog, Long> {
    List<MtMsgsLog> findAllByEtc1(String status);
}
