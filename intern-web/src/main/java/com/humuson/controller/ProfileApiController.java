package com.humuson.controller;

import com.humuson.dto.ProfileSaveRequestDto;
import com.humuson.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProfileApiController {
    private final ProfileService profileService;

    @PostMapping("/profile/create")
    public Long save(@RequestBody ProfileSaveRequestDto requestDto) {
        return profileService.save(requestDto);

    }
}
