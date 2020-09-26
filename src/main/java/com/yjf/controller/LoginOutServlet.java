package com.yjf.controller;


import com.yjf.constans.Constans;
import org.apache.commons.lang.ArrayUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author 余俊锋
 * @date 2020/9/19 11:53
 * @Description
 */
@WebServlet("/loginOut")
public class LoginOutServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(Constans.SESSION_LOGIN);
        clearCookies(req, resp, Constans.COOKIE_LOGIN); //退出登录时删除cookie
        session.setAttribute(Constans.SESSION_LOGOUT, true);
        resp.sendRedirect("/index.jsp");
    }

    public void clearCookies(HttpServletRequest request, HttpServletResponse response, String... cookieNames) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return;
        for (Cookie c : cookies)
            if (ArrayUtils.contains(cookieNames, c.getName())) {
                c.setValue("");
                c.setPath("/");
                c.setMaxAge(1);
                c.setSecure(true);
                response.addCookie(c);
            }
    }
}
