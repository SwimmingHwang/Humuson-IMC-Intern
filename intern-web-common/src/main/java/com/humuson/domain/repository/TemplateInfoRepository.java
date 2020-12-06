package com.humuson.domain.repository;

import com.humuson.domain.entity.TemplateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TemplateInfoRepository  extends JpaRepository<TemplateInfo, Long> {

    @Query(value = "SELECT * FROM imc_template_info where template_content= ?1 ",nativeQuery = true)
    TemplateInfo findByTemplateContent(String templateContent);
}
