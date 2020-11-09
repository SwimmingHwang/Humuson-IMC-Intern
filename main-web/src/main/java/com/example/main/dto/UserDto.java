package com.example.main.dto;

import com.example.main.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto { // form 에서 입력 받은 정보를 담은 Dto를 받아 Entitiy 객체로 변환
    private Long id;
    private String username;
    private String email;
    private String password;
    private String authority;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(id)
                .username(username)
                .email(email)
                .password(password)
                .authority(authority)
                .build();
    }

    @Builder
    public UserDto(Long id, String username, String email, String password, String authority) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }
}