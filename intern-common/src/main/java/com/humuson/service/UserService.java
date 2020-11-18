package com.humuson.service;

import com.humuson.domain.Entity.UserEntity;
import com.humuson.domain.Repository.UserRepository;
import com.humuson.domain.Role;
import com.humuson.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
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
        UserEntity userEntity = userEntityWrapper.orElseThrow(() -> new UsernameNotFoundException(userEmail));
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userEntity.getAuthority().equals("ROLE_ADMIN")) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    // 현재 사용자가 admin 권한을 가지고 있는지 검사
    public static boolean hasAdminRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().filter(o -> o.getAuthority().equals(Role.ADMIN.getValue())).findAny().isPresent();
    }

}