package com.example.main.auth;

import com.example.main.domain.Role;
import com.example.main.domain.entity.UserEntity;
import com.example.main.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserAuthenticationProvider  implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("unchecked")
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userEmail = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        String secPassword = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            secPassword = String.format("%064x", new BigInteger(1, md.digest()));
            System.out.println(secPassword);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        System.out.println("================ "+userEmail+" ==============");
        System.out.println("================ "+secPassword+" ==============");

        Optional<UserEntity> userEntityWrapper = userRepository.findByEmail(userEmail);
        UserEntity userEntity = null;
        List<GrantedAuthority> authorities = new ArrayList<>();

        System.out.println("================ provider 진입 ==============");
        if(userEntityWrapper.isPresent()) {
            userEntity = userEntityWrapper.get();

            if(userEntity.getPassword().equals(secPassword)) {

                if (("admin@example.com").equals(userEmail)) {
                    authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
                } else {
                    authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
                }
            }else{
                throw new BadCredentialsException(userEmail);
            }
        }else{
            throw new BadCredentialsException(userEmail);
        }

        User user = new User(userEntity.getEmail(), userEntity.getPassword(), authorities);

        return new UsernamePasswordAuthenticationToken(userEmail, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}