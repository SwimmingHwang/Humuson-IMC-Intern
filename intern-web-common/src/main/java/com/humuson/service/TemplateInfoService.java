package com.humuson.service;

import com.humuson.domain.entity.TemplateInfo;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.repository.TemplateInfoRepository;
import com.humuson.dto.at.AtMsgsResponseDto;
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
    @Transactional(readOnly = true)
    public TemplateInfo findByTemplateContent(String name) {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return templateInfoRepository.findByTemplateContent(name);
    }
    @Transactional(readOnly = true)
    public TemplateInfo findById(long id) {
        TemplateInfo entity = templateInfoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return entity;
    }

}