package com.example.main.controller;

import com.example.main.dto.UserDto;
import com.example.main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "user/signup";
    }

    // 회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(UserDto userDto) throws Exception{
        userService.joinUser(userDto);

        return "redirect:/user/login";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "user/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        return "user/loginSuccess";
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
        return "admin";
    }
}