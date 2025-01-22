package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.InsertBoardDto;
import com.korit.servlet_study.dto.ResponseDto;
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
        InsertBoardDto insertBoardDto = objectMapper.readValue(response.toString(), InsertBoardDto.class);
        // StringBuilder에 담긴 JSON 문자열을 InsertBoardDto 객체로 변환합니다.

        ResponseDto<?> responseDto = boardService.insertBoard(insertBoardDto);
// boardService의 insertBoard 메서드를 호출하여 insertBoardDto 데이터를 기반으로 게시글 삽입 작업을 수행하고,
// 결과를 담은 ResponseDto 객체를 반환받음. (ResponseDto는 상태 코드와 메시지, 데이터를 담는 DTO로 보임)

        String responseJson = objectMapper.writeValueAsString(responseDto);
// ObjectMapper를 사용하여 ResponseDto 객체를 JSON 문자열로 변환.
// 이는 클라이언트와의 통신에서 JSON 형태로 데이터를 전달하기 위해 사용됨.

        response.setStatus(responseDto.getStatus());
// 클라이언트에 반환할 HTTP 응답 상태 코드를 설정.
// ResponseDto의 상태 값을 기반으로 설정하며, 예를 들어 200(성공) 또는 400(오류)이 될 수 있음.

        response.setContentType("application/json");
// 응답의 Content-Type을 JSON 형식으로 설정.
// 클라이언트가 이 응답이 JSON 데이터임을 알 수 있도록 명시함.

        response.getWriter().write(responseJson);
// HTTP 응답의 body에 JSON 문자열(responseJson)을 작성하여 클라이언트로 전달.
// 이를 통해 클라이언트는 JSON 형태로 변환된 응답 데이터를 수신할 수 있음.
    }
}

