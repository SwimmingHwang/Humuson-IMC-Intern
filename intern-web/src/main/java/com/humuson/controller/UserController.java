package com.humuson.controller;

import com.humuson.domain.Role;
import com.humuson.dto.UserDto;
import com.humuson.service.UserService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Map;

//@Tag(name="사용자", description = "사용자 정보를 관리합니다.")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

//    @Operation(summary="회원가입 페이지")
    @GetMapping("/user/sign-up")
    public String dispSignup() {
        return "user/sign-up";
    }

//    @Operation(summary="회원가입 처리")
//    @PostMapping("/user/sign-up")
//    public String execSignup(UserDto userDto) {
//        userService.saveMemberUser(userDto);
//        return "redirect:/user/login";
//    }

    @PostMapping("/user/sign-up")
    public String execSignup(@Valid UserDto userDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("userDto", userDto);
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "user/sign-up";
        }
        userService.saveMemberUser(userDto);
        return "redirect:/user/login";
    }

//    @Operation(summary="로그인 페이지")
    @GetMapping("/user/login")
    public String dispLogin() {
        return "user/login";
    }

//    @Operation(summary="로그인 처리")
    @PostMapping("/user/login")
    public String execLogin(UserDto userDto) {
        if(userDto.getAuthority().equals(Role.MEMBER.getValue())) {
            return "member/member-page";
        } else {
            return "redirect:/user/login";
        }
    }

//    @Operation(summary="로그아웃 결과 페이지")
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "user/login";
    }

//    @Operation(summary="접근 거부 페이지")
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "user/denied";
    }

//    @Operation(summary="")
    @GetMapping("/member")
    public String dispAdmin() {
        return "member/member-page";
    }

    @GetMapping("/profile/create")
    public String profileCreate(){
        return "profile/create";
    }
}