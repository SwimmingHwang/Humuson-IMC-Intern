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

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        if(userService.hasAdminRole()) {
            return "redirect:/user/admin";
        } else {
            return "redirect:/user/member";
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

    @GetMapping("/user/admin")
    public String dispAdmin() {
        return "index";
    }

    @GetMapping("/user/member")
    public String dispMember() {
        return "index";
    }

}