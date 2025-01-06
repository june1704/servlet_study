package com.korit.servlet_study.server_flow;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class TestFilter {
    public static void doFilter(Request req, Response res) {
        System.out.println("전처리");

        TestServlet.doGet(req, res);

        System.out.println("후처리");
    }
}
