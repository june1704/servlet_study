<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-01-03
  Time: 오후 5:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

    %> <!-- 스크립 틀릿 -->
    <h1>Hello Servlet</h1>
    <h2>이름: <%=name %></h2>
    <h2>나이: <%=age %></h2>
</body>
</html>

<!--
1. java 기초
2. HTML, CSS, JS
3. Servlet, JSP
4. MySQL(Database) - AWS RDS
5. Java JDBC(JAVA Database Connector)
6. React(JS) - AWS S3 배포 -> https
7. ServerStateManagement(React Query)
8. ClientStateManagement(Recoil)
9. SpringBoot - AOP, Validation, SpringBoot Security JWT, Swagger, EMail, payment(portOne)
10. MybatisFramework(JDBC 라이브러리), JPA
11. 배포 - ec2(우분투) + Docker + nginx + github Actions + https
-->

