package com.humuson.agent.domain.repository;

import com.humuson.agent.domain.entity.FtReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FtReportRepository extends JpaRepository<FtReport, Long> {
    List<FtReport> findAllByEtc1(String status);
}
