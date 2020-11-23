package com.humuson.domain.repository;

import com.humuson.domain.entity.FtMsgsLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FtMsgsLogRepository extends JpaRepository<FtMsgsLog, Long> {
    List<FtMsgsLog> findAllByEtc1(String status);
}
