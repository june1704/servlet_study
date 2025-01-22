package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardDao {
    // DBConnectionMgr 객체를 저장할 변수 선언
    private DBConnectionMgr dbConnectionMgr;
    // BoardDao의 싱글톤 인스턴스를 저장할 변수 선언
    private static BoardDao instance;

    // 생성자 - DBConnectionMgr 인스턴스를 초기화
    private BoardDao() {
        dbConnectionMgr = DBConnectionMgr.getInstance();
    }

    // BoardDao의 싱글톤 인스턴스를 반환하는 메서드
    public static BoardDao getInstance() {
        // 인스턴스가 null이면 새로 생성하고, 그렇지 않으면 기존 인스턴스를 반환
        if (instance == null) {
            instance = new BoardDao();
        }
        return instance;
    }

    // Board 객체를 DB에 저장하는 메서드
    public Board save(Board board) {
        // 저장된 게시물을 담을 변수 선언
        Board insertedBoard = null;
        // DB 연결, PreparedStatement, ResultSet 객체 선언
        Connection con = null;
        PreparedStatement ps = null;

        try {
            // DB 연결 객체를 얻기 위해 DBConnectionMgr에서 연결을 가져옴
            con = dbConnectionMgr.getConnection();
            // SQL 쿼리문 작성: board_tb 테이블에 데이터를 삽입하는 쿼리
            String sql = """
                    insert into board_tb
                    values(default, ?, ?)
                    """;

            // PreparedStatement 객체를 생성하고, SQL 쿼리를 준비
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // 첫 번째 매개변수에 제목(title) 값을 설정
            ps.setString(1, board.getTitle());
            // 두 번째 매개변수에 내용(content) 값을 설정
            ps.setString(2, board.getContent());
            // SQL 쿼리 실행 (INSERT 쿼리 실행)
            ps.executeUpdate(); // int로 리턴
            // 삽입된 행의 기본 키 값(자동 생성된 boardId)을 가져오기 위해 ResultSet을 사용
            ResultSet rs = ps.getGeneratedKeys();

            // 생성된 기본 키가 있으면 Board 객체에 해당 값을 설정
            if (rs.next()) {
                insertedBoard = Board.builder()
                        .boardId(rs.getInt(1)) // 첫 번째 컬럼은 자동 생성된 boardId
                        .title(board.getTitle()) // 제목
                        .content(board.getContent()) // 내용
                        .build();
            }

        } catch (Exception e) {
            // 예외가 발생하면 RuntimeException으로 감싸서 던짐
            throw new RuntimeException(e);
        } finally {
            // DB 연결 자원을 반환 (연결을 풀에 반환)
            dbConnectionMgr.freeConnection(con, ps);
        }
        // 삽입된 게시물 정보를 Optional로 감싸서 반환
        return insertedBoard;
    }
}
