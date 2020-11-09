package com.example.main.dto;

import com.example.main.domain.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String password;

    @Builder
    public UserDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserEntity toEntity(){
        return UserEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}