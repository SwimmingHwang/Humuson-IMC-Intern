package com.humuson.controller;

import com.humuson.dto.ProfileSaveRequestDto;
import com.humuson.service.ProfileService;
import lombok.RequiredArgsConstructor;
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
