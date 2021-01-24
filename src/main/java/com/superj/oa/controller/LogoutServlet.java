package com.superj.oa.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销功能
 */
@WebServlet(name = "LogoutServlet" , urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //作废当前session
        request.getSession().invalidate();
        //也可以用removeAttribute() 一个个将属性移除 ，但比较麻烦
        response.sendRedirect("/login.html");
    }
}
