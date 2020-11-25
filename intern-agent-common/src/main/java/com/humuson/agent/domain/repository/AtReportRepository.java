package com.humuson.agent.domain.repository;

import com.humuson.agent.domain.entity.AtReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtReportRepository extends JpaRepository<AtReport, Long> {
    List<AtReport> findAllByEtc1(String status);
}
