package com.humuson.config;

import com.humuson.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/resources/**", "/css/**", "/js/**", "/img/**", "/vendor/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").permitAll();
        http.authorizeRequests()
            .antMatchers("/user/**").permitAll()
            //member 권한
            .antMatchers("/member/**").hasRole("MEMBER")
            .antMatchers("/**").authenticated()
        ;
        http.formLogin()
            .loginPage("/user/login")
            .usernameParameter("email").passwordParameter("password") // id, pwd param 변경
                .loginProcessingUrl("/user/login")
//            .defaultSuccessUrl("/user/login/result")
                .failureForwardUrl("/user/login")
        ;
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            .logoutSuccessUrl("/user/logout/result")
            .invalidateHttpSession(true)
        ;
        http.exceptionHandling()
            .accessDeniedPage("/user/denied")
        ;
        http.httpBasic()
            .disable()
        ;
        http.headers() // 기본 보안 암호 사용 제거
            .httpStrictTransportSecurity()
            .disable()
        ;
//        http.sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        //auth.authenticationProvider(authenticationProvider);
    }

}