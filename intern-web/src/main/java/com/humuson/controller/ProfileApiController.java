package com.humuson.controller;

import com.humuson.dto.ProfileSaveRequestDto;
import com.humuson.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="프로필", description = "메시지 발송에 필요한 정보를 관리합니다.")
@RequiredArgsConstructor
@RestController
public class ProfileApiController {
    private final ProfileService profileService;


    @Operation(summary = "프로필 정보 저장")
    @PostMapping("/profile/create")
    public Long save(@RequestBody ProfileSaveRequestDto requestDto) {
        return profileService.save(requestDto);

    }
}
