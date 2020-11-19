package com.humuson.service;

import com.humuson.domain.Entity.TemplateInfo;
import com.humuson.domain.repository.TemplateInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor// final이 선언된 모든 필드를 인자값으로하는 생성자를 생성해줌.
@Service
public class TemplateInfoService {

    private final TemplateInfoRepository templateInfoRepository;

    @Transactional(readOnly = true)
    public List<TemplateInfo> findAll() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return templateInfoRepository.findAll();
    }
}