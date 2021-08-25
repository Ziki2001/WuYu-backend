package com.ziki.holidaywork.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;


@Component
public class PermissionInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] AuthApis = {
                "/api/educationItem/createNewItem",
                "/api/educationItem/:admin",
                "/api/educationItem/image/2021",
                "/api/educationItem/changeImg"
        };
        String requestUrl = request.getRequestURI();
        if (requestUrl.contains(Arrays.toString(AuthApis))) {
            HttpSession session = request.getSession();
            return session.getAttribute("user") != null;
        }
        return true;
    }
//    public PermissionInterceptor() {
//        System.out.println("Hellow world");
//    }
}
