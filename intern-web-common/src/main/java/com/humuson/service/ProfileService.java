package com.humuson.service;

import com.humuson.domain.entity.Group;
import com.humuson.domain.entity.Profile;
import com.humuson.domain.repository.ProfileRepository;
import com.humuson.dto.ProfileSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor// final이 선언된 모든 필드를 인자값으로하는 생성자를 생성해줌.
@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Transactional
    public Long save(ProfileSaveRequestDto requestDto) {
        return profileRepository.save(requestDto.toEntity()).getId(); // insert/update 쿼리 실행
    }
    @Transactional(readOnly = true)
    public Profile findById(long id) {
        Profile entity = profileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return entity;
    }
    @Transactional(readOnly = true)
    public Profile findByUserId(long id) {
        Profile entity = profileRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return entity;
    }
}