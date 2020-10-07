package com.yjf.filter;

import com.yjf.constans.Constans;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 余俊锋
 * @date 2020/9/23 13:28
 * @Description
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestPath = request.getRequestURI();
        if (requestPath.endsWith("/getVerifyCode")||requestPath.endsWith("/forget.jsp")
                ||requestPath.endsWith("/sendEmailCode")||requestPath.endsWith("/updatePassword")
                ||requestPath.contains("/js")||requestPath.contains("/static")||requestPath.contains("weChat")
                ||requestPath.contains("registerUser")||requestPath.contains("register")||requestPath.contains("checkName")
                ||requestPath.contains("QQ")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if (requestPath.endsWith("/")||requestPath.endsWith("/index.jsp")||requestPath.endsWith("/login")){
            if (request.getSession().getAttribute(Constans.SESSION_LOGIN)!=null){
                response.sendRedirect("/html/common/home.jsp");
                return;
            }
        }else{
            if (request.getSession().getAttribute(Constans.SESSION_LOGIN)==null){
                response.sendRedirect("/index.jsp");
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
