package com.example.main.service;

import com.example.main.domain.repository.ProfileRepository;
import com.example.main.dto.ProfileSaveRequestDto;
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
}