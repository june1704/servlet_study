package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.SignupDto;
import com.korit.servlet_study.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/signup")
public class SignupRestServlet extends HttpServlet {

    private AuthService authService;

    public SignupRestServlet() {
        authService = AuthService.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder(); // 문자열 합치기

        try(BufferedReader bufferedReader = request.getReader()) {
//            String line = bufferedReader.readLine(); 한 줄일때 쓴다.
            String line;
            while ((line = bufferedReader.readLine()) != null) { // 여러 줄일때 쓴다.
                stringBuilder.append(line);
            }
        }

        ObjectMapper  objectMapper = new ObjectMapper();
        SignupDto signupDto = objectMapper.readValue(stringBuilder.toString(), SignupDto.class);

        ResponseDto<?> responseDto = authService.signup(signupDto);
        response.setContentType("application/json");
        response.setStatus(responseDto.getStatus());
        response.getWriter().println(objectMapper.writeValueAsString(responseDto));
    }
}
