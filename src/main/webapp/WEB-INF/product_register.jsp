<%@ page import="com.korit.servlet_study.entity.Category" %>
<%@ page import="com.korit.servlet_study.datas.DataList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-01-06
  Time: 오전 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>${serverName}</title>
</head>
<body>
    <h1>상품등록</h1>
    <table>
        <tr>
            <td>카테고리</td>
            <td>
                <select name="" id="">
<%--
    [EL 표현식]
    EL 표현식은 라이브러리 필요
    표현방식: ${}
    EL 표현식을 사용할 수 있는 경우
    1. Attribute에 포함 되어 있는 값(Application, Session, Request)
    2. JSP의 기본 설정 값
--%>
<%--
    서블릿
    저장소 1. Application: 서버 실행(전역)
          2. Session: 브라우저에서 서버로 최초 요청(전역(개인 저장소)인증)
          3. Request: 매번 요청 시(전역)

            setAttribute("key값", 값(object);
--%>
<%--    for(Category category : categories) --%>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}">${category.name}</option> <!--EL 표현식 -->
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>상품명</td>
            <td><input type="text"></td>
        </tr>
        <tr>
            <td>가격</td>
            <td><input type="text"></td>
        </tr>
        <tr>
            <td>등록일자</td>
            <td><input type="date"></td>
        </tr>
    </table>
</body>
</html>
