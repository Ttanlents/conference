package com.yjf.controller;


import com.yjf.constans.Constans;
import com.yjf.entity.User;
import com.yjf.services.UserService;
import com.yjf.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author 余俊锋
 * @date 2020/9/22 13:05
 * @Description
 */
@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {

    UserService userService=UserService.getInstance();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String code=request.getParameter("code");
        User loginUser = userService.login(name, password);
        String verifyCode=(String) request.getSession().getAttribute(Constans.SESSION_CODE);
        if (loginUser!=null&&verifyCode.equalsIgnoreCase(code)){
            String remember=request.getParameter("remember");
            if (remember!=null&&!remember.equals("")){
                int number = Integer.parseInt(remember);
                if (number==1){
                    Cookie cookie = new Cookie(Constans.COOKIE_LOGIN, URLEncoder.encode(JsonUtils.pojoToJson(loginUser), "utf-8"));
                    int time=60*60*24*7;
                    cookie.setMaxAge(time);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
            request.getSession().setAttribute(Constans.SESSION_LOGIN,loginUser);
            response.sendRedirect("/html/common/home.jsp");
        }else {
            response.setHeader("refresh","2;url=/index.jsp");
            response.getOutputStream().write("账号或密码错误！".getBytes());
        }

    }
}
