package com.example.main.controller;

import com.example.main.domain.entity.UserEntity;
import com.example.main.dto.UserDto;
import com.example.main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    // 회원가입 페이지
    @GetMapping("/user/sign-up")
    public String dispSignup() {
        return "user/sign-up";
    }

    // 회원가입 처리
    @PostMapping("/user/sign-up")
    public String execSignup(UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/user/login";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "user/login";
    }

    // 로그인 처리
    @PostMapping("/user/login")
    public String execLogin(UserDto userDto) {
        userService.loadUserByUsername(userDto.getEmail());
        return "user/login/result";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult(UserDto userDto) {
        if(userService.getUserAuthority(userDto).equals("ROLE_ADMIN")) {
            return "redirect:/admin/admin-page";
        } else {
            return "redirect:/member/member-page";
        }
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "user/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "user/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "user/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "admin/admin-page";
    }

    // 멤버 페이지
    @GetMapping("/member")
    public String dispMember() {
        return "member/member-page";
    }
}