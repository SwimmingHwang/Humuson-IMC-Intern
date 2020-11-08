package com.example.main.dto;

import com.example.main.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public UserDto(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}