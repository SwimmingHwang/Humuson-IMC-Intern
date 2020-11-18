package com.humuson.dto;

import com.humuson.domain.Entity.UserEntity;
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
    private String authority;

    @Builder
    public UserDto(String username, String email, String password, String authority) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    public UserEntity toEntity(){
        return UserEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .authority(authority)
                .build();
    }
}