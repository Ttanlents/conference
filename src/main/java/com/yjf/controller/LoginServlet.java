package com.yjf.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 余俊锋
 * @date 2020/9/22 13:05
 * @Description
 */
@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {

    public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      req.getRequestDispatcher("/html/common/home.jsp").forward(req,res);
    }
}
