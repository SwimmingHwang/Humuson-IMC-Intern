package com.humuson.config;

import com.humuson.domain.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return true;
        }

        request.getSession().setMaxInactiveInterval(60*60);
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.MEMBER.getValue()))) {
            response.sendRedirect("/");
            return false;
        } else {
            return true;
        }
    }
}
