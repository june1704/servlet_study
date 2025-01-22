package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.BoardDao;
import com.korit.servlet_study.dto.InsertBoardDto;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.entity.Board;

public class BoardService {  // BoardService 클래스 선언, 게시판 관련 비즈니스 로직을 담당
    private BoardDao boardDao;  // BoardDao 객체 선언, 데이터베이스와의 상호작용을 담당

    private static BoardService instance;  // BoardService의 싱글톤 인스턴스를 저장할 정적 변수 선언

    private BoardService() {  // 생성자, BoardDao 객체를 초기화
        boardDao = BoardDao.getInstance();  // BoardDao의 인스턴스를 가져와 boardDao에 할당
    }

    public static BoardService getInstance() {  // 싱글톤 패턴을 사용하여 BoardService의 인스턴스를 반환하는 메서드
        if (instance == null) {  // 인스턴스가 아직 생성되지 않았다면
            instance = new BoardService();  // 새로 생성
        }
        return instance;  // 이미 생성된 인스턴스를 반환
    }

    public ResponseDto<?> insertBoard(InsertBoardDto dto) {  // 게시글 삽입을 처리하는 메서드, InsertBoardDto를 입력받음
        Board board = dto.toBoard();  // InsertBoardDto 객체를 Board 엔티티 객체로 변환
        Board insertedboard = boardDao.save(board);  // Board 엔티티를 BoardDao의 save 메서드를 통해 데이터베이스에 저장
        if(insertedboard == null) {  // 삽입된 게시글이 null인 경우, 즉 삽입 실패한 경우
            return ResponseDto.fail("게시글 작성 실패");  // 실패 응답을 반환
        }
        return ResponseDto.success(insertedboard);  // 삽입된 게시글을 포함한 성공 응답을 반환
    }
}
