package com.example.main.service;

import com.example.main.domain.Role;
import com.example.main.domain.entity.UserEntity;
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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService/* implements UserDetailsService */{
    private UserRepository userRepository;

    @Transactional
    public Long joinUser(UserDto memberDto) throws Exception {
        // 비밀번호 암호화
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(memberDto.getPassword().getBytes());
        String secPassword = String.format("%064x", new BigInteger(1, md.digest()));

        memberDto.setPassword(secPassword);

        return userRepository.save(memberDto.toEntity()).getId();
    }

   /* @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityWrapper = userRepository.findByEmail(userEmail);
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }*/
}