package com.humuson.controller;

import com.humuson.domain.Role;
import com.humuson.dto.UserDto;
import com.humuson.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String dashboard() {
        return "redirect:/admin";
    }

    // 회원가입 페이지
    @GetMapping("/user/sign-up")
    public String dispSignup() {
        return "user/sign-up";
    }

    // 회원가입 처리
    @PostMapping("/user/sign-up")
    public String execSignup(UserDto userDto) {
        userService.saveAdminUser(userDto);
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
        if(userDto.getAuthority().equals(Role.ADMIN.getValue())) {
            return "admin";
        } else {
            return "redirect:/user/login";
        }
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        return "redirect:/admin";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "user/login";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "user/denied";
    }

    @GetMapping("/admin")
    public String dispAdmin() {
        return "admin/dashboard";
    }

}