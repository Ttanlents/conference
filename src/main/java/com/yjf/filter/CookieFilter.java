package com.yjf.filter;

import com.yjf.constans.Constans;
import com.yjf.entity.User;
import com.yjf.utils.JsonUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author 余俊锋
 * @date 2020/9/23 12:36
 * @Description
 */
@WebFilter("/*")
public class CookieFilter implements Filter {

/**
 *@Description 如果检测到访问的用户未登录，判断是否有cookie,使用cookie登录
 *@author 余俊锋
 *@date 2020/9/23 13:34
 *@params servletRequest
 * @param servletResponse
 * @param filterChain
 *@return void
 */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url=request.getRequestURI();
        if (url.endsWith("/loginOut")||url.endsWith("/login")||
                url.endsWith("/getVerifyCode")||url.endsWith("/forget.jsp")
                ||url.endsWith("/sendEmailCode")||url.endsWith("/updatePassword")||url.contains("/static")
                ||url.contains("/js")||url.contains("/weChat")||url.contains("registerUser")||url.contains("register")||url.contains("checkName")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if (request.getSession().getAttribute(Constans.SESSION_LOGIN)==null){

            if(request.getRequestURI().endsWith("/index.jsp")){
                Boolean flag =(Boolean) request.getSession().getAttribute(Constans.SESSION_LOGOUT);
                if (flag!=null&&flag){
                    filterChain.doFilter(servletRequest,servletResponse);
                    return;
                }

            }
            Cookie[] cookies = request.getCookies();
            if (cookies!=null&&cookies.length>0){
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if (Constans.COOKIE_LOGIN.equals(name)){
                        String value = cookie.getValue();
                        try {
                            User user= JsonUtils.jsonToPojo(URLDecoder.decode(value,"UTF-8"),User.class);
                            request.getSession().setAttribute(Constans.SESSION_LOGIN,user);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

       filterChain.doFilter(servletRequest,servletResponse);

    }
}
