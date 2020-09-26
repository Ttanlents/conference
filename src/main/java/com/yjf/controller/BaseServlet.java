package com.yjf.controller;

import com.yjf.constans.Constans;
import com.yjf.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author 余俊锋
 * @date 2020/9/22 13:05
 * @Description
 */
public class BaseServlet extends HttpServlet {
    public HttpSession baseSession;
    public User baseUser;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.baseSession = req.getSession();
        this.baseUser=(User) baseSession.getAttribute(Constans.SESSION_LOGIN);
        Class clazz = this.getClass();
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String method = split[split.length - 1];
        try {
            Method declaredMethod = clazz.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
