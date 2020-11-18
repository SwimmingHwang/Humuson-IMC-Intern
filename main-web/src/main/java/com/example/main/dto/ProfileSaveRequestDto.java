package com.example.main.dto;


import com.example.main.domain.entity.Profile;
import com.example.main.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileSaveRequestDto {
    private String profileId;
    private String phoneNumber;
    private String senderKey;
    private String senderName;
    private User user;

    @Builder
    public ProfileSaveRequestDto(String profileId, String phoneNumber, String senderKey, String senderName)  {
        this.profileId = profileId;
        this.phoneNumber = phoneNumber;
        this.senderKey = senderKey;
        this.senderName = senderName;
    }
    public Profile toEntity(){
        return Profile.builder()
                .profileId(profileId)
                .phoneNumber(phoneNumber)
                .senderKey(initSenderKey())
                .senderName(senderName)
                .build();
    }
    // TODO : SenderKey 발급 과정 생략
    private String initSenderKey(){
        senderKey = "54ef196697bda7dbc36a45a334beb83580d8ca2a";
        return senderKey;
    }
}
