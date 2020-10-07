package com.yjf.controller;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.yjf.constans.Constans;
import com.yjf.entity.User;
import com.yjf.services.UserService;
import com.yjf.utils.EmojiUtil;
import com.yjf.utils.JsonUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/QQ/*")
public class QQLoginServlet extends BaseServlet {
    UserService userService = new UserService();

    /**
     * QQ登录二维码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void toQQLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileInputStream fis = null;
        // 加载配置文件
        fis = new FileInputStream(this.getClass().getResource("/").getPath() + "qqconnectconfig.properties");
        Properties prop = new Properties();
        prop.load(fis);
        //qq应用AppID
        String appId = prop.getProperty("app_ID");
        //qq授权成功后的回调地址
        String redirect_URI = prop.getProperty("redirect_URI");


        //Ste2：获取Authorization Token
        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code" +
                "&client_id=" + appId +
                "&redirect_uri=" + redirect_URI +
                "&state=test";
        response.sendRedirect(url);
        //将页面重定向到qq第三方的登录页面

    }

    //回调地址
    public void callback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileInputStream fis = null;
        // 加载配置文件
        fis = new FileInputStream(this.getClass().getResource("/").getPath() + "qqconnectconfig.properties");
        Properties prop = new Properties();
        prop.load(fis);
        String appId = prop.getProperty("app_ID");
        String app_KEY = prop.getProperty("app_KEY");
        String redirect_URI = prop.getProperty("redirect_URI");

        String code = request.getParameter("code");
        System.out.println("qq登录成功，返回code" + code);

        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" +
                "&client_id=" + appId +
                "&client_secret=" + appId +
                "&code=" + code +
                "&redirect_uri" + redirect_URI;

        Map jsonObjectForQQ = JsonUtils.getJsonObjectForQQ(url);
        String access_token = (String) jsonObjectForQQ.get("access_token");
        System.out.println("获取token:" + access_token);
        //获取Open_ID
        String url2 = "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
        Map OpenID = JsonUtils.getJsonObjectForQQ(url2);
        String openid = (String) jsonObjectForQQ.get("openid");
        System.out.println("获取:" + openid);

        String getUserInfo = "https://graph.qq.com/user/get_user_info?access_token=" + access_token +
                "&oauth_consumer_key=" + app_KEY +
                "&openid=" + openid;
        Map userInfo = JsonUtils.getJsonObjectForQQ(getUserInfo);
        System.out.println("用户信息:");
        System.out.println(userInfo);
        User user = userService.getUserByOpenQqId(openid);
        if (user==null){
            if (userInfo.get("ret").equals("0")) {
                 user = new User();
                String userName = (String) userInfo.get("nickname");
                String gender = (String) userInfo.get("gender");
                String headImage = (String) userInfo.get("figureurl_qq_1");
                // 用户的头像
                user.setPic(headImage);
                // 性别
                user.setGender(gender);
                // 用户的昵称
                String realName = userName;
                String replaceName = EmojiUtil.replaceEmoji(realName);
                user.setRealName(replaceName);
                // 随机用户名(11位随机字符串)
                user.setUsername(UUID.randomUUID().toString().substring(36 - 15));
                user.setQqOpenid(openid);
                // 注册一个新的用户
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                user.setRegisterTime(dateFormat.format(new Date()));
                userService.addUser(user);
            } else {
                System.out.println("状态码:" + userInfo.get("ret"));
            }
        }
        baseSession.setAttribute(Constans.SESSION_LOGIN, user);
        response.sendRedirect("/html/common/home.jsp");

    }


}
