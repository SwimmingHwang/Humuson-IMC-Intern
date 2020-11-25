package com.humuson.agent.domain.repository;

import com.humuson.agent.domain.entity.MtReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MtReportRepository extends JpaRepository<MtReport, Long> {
    List<MtReport> findAllByEtc1(String status);
}
