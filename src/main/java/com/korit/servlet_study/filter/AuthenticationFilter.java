package com.korit.servlet_study.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dao.UserDao;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.security.annotation.JwtValid;
import com.korit.servlet_study.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class AuthenticationFilter implements Filter {
    private JwtProvider jwtProvider;
    private UserDao userDao;

    public AuthenticationFilter() {
        jwtProvider = JwtProvider.getInstance();
        userDao = UserDao.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            if(isJwtTokenValid(request)) { // 토큰 검사를 해야 되니?
                String bearerToken = request.getHeader("Authorization");
                if (bearerToken == null) {
                    setUnAuthenticatedResponse(response); // 토큰 없으면 403에러
                    return;
                }
                Claims claims = jwtProvider.parseToken(bearerToken); // 유효기간 검사
                if (claims == null) {
                    setUnAuthenticatedResponse(response);
                    return;
                }

                int userId = Integer.parseInt(claims.get("userId").toString()); // 로그인 검사
                User foundUser = userDao.findById(userId);
                if(foundUser == null) {
                    setUnAuthenticatedResponse(response);
                    return;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        filterChain.doFilter(servletRequest, servletResponse); // 다음단계 필터로 넘어감
    }

        // 없어도 응답
    private boolean isJwtTokenValid(HttpServletRequest request) throws ClassNotFoundException {
        String method = request.getMethod(); // 어느 메서드에
        String servletPath = request.getHttpServletMapping().getServletName(); // 어느 서블릿에 요청 날렸니?

        Class<?> servletClass = Class.forName(servletPath); //
        Method foundmethod1 = getMappedMethod(servletClass, method);
        return foundmethod1 != null;
    }
//getDeclaredMethods() 정의 되어 있는 메서드
    private Method getMappedMethod(Class<?> clazz, String methodName) {
        for(Method method : clazz.getDeclaredMethods()) {
            if(method.getName().toLowerCase().endsWith(methodName.toLowerCase())
                    && method.isAnnotationPresent(JwtValid.class)) { // 해당 메소드에 JwtValid 달려있는 어노테이션을 가지고 있니?
                return method;
            }
        }
        return null;
    }

    private void setUnAuthenticatedResponse(HttpServletResponse response ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto<String> responseDto = ResponseDto.forbidden("검증 할 수 없는 Access Token 입니다.");
        response.setStatus(responseDto.getStatus());
        response.setContentType("application/json");
        response.getWriter().print(objectMapper.writeValueAsString(responseDto));
        return;
    }
}
