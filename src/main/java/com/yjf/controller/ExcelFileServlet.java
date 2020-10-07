package com.yjf.controller;

import com.yjf.entity.ExportExcel;
import com.yjf.entity.Page;
import com.yjf.entity.User;
import com.yjf.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/export/*")
public class ExcelFileServlet extends BaseServlet {
    UserService userService=new UserService();
    public void getExcelFile(HttpServletRequest req, HttpServletResponse resp){
        String username = req.getParameter("username");
        String pageCurrent = req.getParameter("pageCurrent");
        if (username == null) {
            username = "";
        }
        if (pageCurrent == null) {
            pageCurrent = "1";
        }
        int count = userService.getCount(username);
        Page<User> page = new Page<>(Integer.parseInt(pageCurrent), 5, count);
        List<User> userList = userService.getUserList(username, page);
        ExportExcel<User> ee= new ExportExcel();
        String[] headers = { "序号", "用户名", "邮箱", "真实姓名","年龄","性别","注册时间","部门" };
        String[] headersName = { "id", "username", "email", "realName","age","gender","registerTime","deptName" };
        String fileName = "用户信息表";
        ee.exportExcel(headers,headersName,userList,fileName,resp);
    }
}
