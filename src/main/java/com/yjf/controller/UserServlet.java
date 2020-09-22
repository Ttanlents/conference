package com.yjf.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 余俊锋
 * @date 2020/9/22 14:31
 * @Description
 */
@WebServlet("/user/list")
public class UserServlet extends BaseServlet{
    public void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("查询成功");
    }
}
