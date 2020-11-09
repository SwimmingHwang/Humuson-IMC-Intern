package com.example.main.service;

import com.example.main.domain.Role;
import com.example.main.domain.entity.UserEntity;
import com.example.main.domain.msgs.Msgs;
import com.example.main.domain.repository.UserRepository;
import com.example.main.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    /**
     * 회원정보 저장
     *
     * @param userDto 회원정보가 들어있는 DTO
     * @return 저장되는 회원의 PK
     */
    @Transactional
    public Long saveUser(UserDto userDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userDto.toEntity()).getId();

//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        md.update(userDto.getPassword().getBytes());
//        String secPassword = String.format("%064x", new BigInteger(1, md.digest()));
//
//        userDto.setPassword(secPassword);
//
//        return userRepository.save(userDto.toEntity()).getId();
    }


    /**
     * Spring Security 필수 메소드 구현
     * 상세 정보 조회
     *
     * @param userEmail 이메일
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityWrapper = userRepository.findByEmail(userEmail);
        System.out.println("11111111111111111"  +  userEmail);
        UserEntity userEntity = userEntityWrapper.orElseThrow(() -> new UsernameNotFoundException(userEmail));
        System.out.println("222222222222222222222" + userEntity.getEmail());
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userEntity.getAuthority().equals("ROLE_ADMIN")) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        System.out.println("3333333333333333333333" + userEntity.getAuthority());
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

}