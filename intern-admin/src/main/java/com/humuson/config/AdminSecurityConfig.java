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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

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
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/monitor/**").permitAll() // acturator의 endpoint에 모두 접근하게 허용
            .antMatchers("/sba/**").permitAll()
            .anyRequest().hasRole("ADMIN")
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
            .invalidateHttpSession(true) // 로그아웃 시 세션 제거
//            .deleteCookies("JSESSIONID") // 쿠키 제거
//            .clearAuthentication(true)  // 권한 정보 제거
//            .permitAll()
        ;
        http.exceptionHandling()
            .accessDeniedPage("/user/denied")
        ;
        http.httpBasic()
//            .disable()
        ;
        http.headers() // 기본 보안 암호 사용 제거
            .httpStrictTransportSecurity()
            .disable()
        ;
//        http.sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .maximumSessions(1) // 세션 1개만 유지
//            .maxSessionsPreventsLogin(true); // 동시 접속 세션수 제한
//        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        //auth.authenticationProvider(authenticationProvider);
    }

}