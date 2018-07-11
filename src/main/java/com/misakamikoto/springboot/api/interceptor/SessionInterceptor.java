package com.misakamikoto.springboot.api.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // session검사
        HttpSession session = request.getSession(false);

        if(session == null) {
            response.sendRedirect("/logout");
            return false;

        } else {
            return true;
        }
    }
}
