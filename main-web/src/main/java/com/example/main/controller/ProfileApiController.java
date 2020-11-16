package com.example.main.controller;

import com.example.main.domain.entity.UserEntity;
import com.example.main.dto.*;
import com.example.main.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProfileApiController {
    private final ProfileService profileService;

    @PostMapping("/profile/create")
    public Long save(@RequestBody ProfileSaveRequestDto requestDto) {
        return profileService.save(requestDto);

    }
}
