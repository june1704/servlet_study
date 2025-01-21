package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.InsertBoardDto;
import com.korit.servlet_study.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/board")  // 이 서블릿을 "/api/board" URL 패턴으로 매핑합니다.
public class BoardRestServlet extends HttpServlet {  // HttpServlet을 상속하여 서블릿을 정의합니다.
    private BoardService boardService;  // BoardService 객체를 필드로 선언합니다.

    public BoardRestServlet() {  // 서블릿의 생성자입니다.
        boardService = BoardService.getInstance();  // BoardService의 인스턴스를 가져와 boardService에 할당합니다.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // HTTP POST 요청을 처리하는 메서드입니다.
        StringBuilder stringBuilder = new StringBuilder();  // 요청 본문을 저장할 StringBuilder 객체를 생성합니다.
        try (BufferedReader bufferedReader = request.getReader()) {  // 요청 본문을 읽기 위한 BufferedReader 객체를 생성하고, try-with-resources 구문을 사용하여 자동으로 리소스를 관리합니다.
            String line;  // 한 줄씩 읽기 위한 변수 선언
            while ((line = bufferedReader.readLine()) != null) {  // 요청 본문을 한 줄씩 읽어서
                stringBuilder.append(line);  // 읽은 줄을 StringBuilder에 추가합니다.
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();  // JSON 문자열을 객체로 변환하기 위한 ObjectMapper 객체를 생성합니다.
        InsertBoardDto insertBoardDto = objectMapper.readValue(stringBuilder.toString(), InsertBoardDto.class);  // StringBuilder에 담긴 JSON 문자열을 InsertBoardDto 객체로 변환합니다.
    }
}

