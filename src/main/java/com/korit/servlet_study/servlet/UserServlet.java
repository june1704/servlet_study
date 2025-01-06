package com.korit.servlet_study.servlet;

import com.korit.servlet_study.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        List<User> users = new ArrayList<>();
        users.add(new User("aaa", "1111", "aaaaaa", "aaa@gamil.com"));
        users.add(new User("bbb", "1111", "bbbbbb", "bbb@gamil.com"));
        users.add(new User("ccc", "1111", "cccccc", "ccc@gamil.com"));
        users.add(new User("ddd", "1111", "dddddd", "ddd@gamil.com"));
        users.add(new User("eee", "1111", "eeeeee", "eee@gamil.com"));

        config.getServletContext().setAttribute("users", users);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        List<User> users = (List<User>) servletContext.getAttribute("users");
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
    }
}
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//
//        List<String> datas = List.of(username, password, name, email);
//        for(String data : datas) {
//            if(data == null) {
//                response.getWriter().println("<script>alert("")</script>");
//            }
//            if(Data.isBl)
//        }
//        request.setAttribute("username", request.getParameter("username"));
//        request.setAttribute("password", request.getParameter("password"));
//        request.setAttribute("name", request.getParameter("name"));
//        request.setAttribute("email", request.getParameter("email"));
//        request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);


