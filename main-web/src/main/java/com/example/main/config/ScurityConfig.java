package com.example.main.config;

import com.example.main.auth.UserAuthenticationProvider;
import com.example.main.auth.UserAuthenticationSuccessHandler;
import com.example.main.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;


    private UserAuthenticationProvider authenticationProvider;
/*

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/member/**").hasRole("MEMBER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                // .antMatchers("/user/myinfo").hasRole("MEMBER")
                .antMatchers("/**").permitAll() // 모두 접근 가능
                .and() // 로그인 설정
                .formLogin()
                .loginPage("/user/login")
//                .defaultSuccessUrl("/user/login/result")
                .permitAll()
                .successHandler(authenticationSuccessHandler())
                .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/logout/result")
                .invalidateHttpSession(true)
                .and()
                // csrf 적용
                .csrf()
                .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/user/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(authenticationProvider);
    }

    /* * SuccessHandler bean register */
/*
    @Bean
    public AuthSuccessHandler authSuccessHandler() {
        return new AuthSuccessHandler();
    }
    */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        UserAuthenticationSuccessHandler successHandler = new UserAuthenticationSuccessHandler();
//        successHandler.setDefaultTargetUrl("/index");
        return successHandler;
    }

}