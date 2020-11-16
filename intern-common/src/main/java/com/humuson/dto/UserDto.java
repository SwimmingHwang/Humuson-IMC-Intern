package com.humuson.dto;

import com.humuson.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto { // view layer 와 데이터 주고 받을 때 사용
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String authority;
    private Integer status;

    @Builder
    public UserDto(String username, String email, String password, String phoneNumber, String authority, Integer status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
        this.status = status;
    }

    public User toEntity(){
        return User.builder()
                .username(username)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .authority(authority)
                .status(status)
                .build();
    }
}