package com.example.main.domain.repository;

import com.example.main.domain.entity.TemplateInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateInfoRepository  extends JpaRepository<TemplateInfo, Long> {
}
