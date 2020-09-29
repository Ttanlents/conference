package com.yjf.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjf.constans.Constans;
import com.yjf.entity.User;
import com.yjf.services.UserService;
import com.yjf.utils.EmojiUtil;
import com.yjf.utils.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 余俊锋
 * @date 2020/9/22 14:31
 * @Description
 */
@WebServlet("/weChat/*")
public class WeChatServlet extends BaseServlet {


    UserService userService = new UserService();


    protected void wxLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileInputStream fis = null;
        // 加载配置文件
        fis = new FileInputStream(this.getClass().getResource("/").getPath() + "wx.properties");
        Properties prop = new Properties();
        prop.load(fis);
        //微信应用AppID
        String appId = prop.getProperty("wx.AppID");
        //微信授权成功后的回调地址
        String redirectUri = prop.getProperty("wx.redirect_uri");

        //固定格式
        //Step1：获取Authorization Code
        //根据官方文档需要四个参数，网站的scope为snsapi_login
        String url = "https://open.weixin.qq.com/connect/qrconnect?response_type=code" +
                "&appid=" + appId +
                "&redirect_uri=" + URLEncoder.encode(redirectUri) +
                "&scope=snsapi_login";

        // 重定向到微信登录指定的地址进行微信登录授权,授权成功后返回code
        response.sendRedirect(url);
    }


    protected void wxLoginCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileInputStream fis = null;
        // 加载配置文件
        fis = new FileInputStream(this.getClass().getResource("/").getPath() + "wx.properties");
        Properties prop = new Properties();
        prop.load(fis);
        String appId = prop.getProperty("wx.AppID");
        String appSecret = prop.getProperty("wx.AppSecret");
        //获取微信授权后返回的code
        String code = request.getParameter("code");
        System.out.println("登录成功，返回code" + code);
        //固定格式
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId +
                "&secret=" + appSecret +
                "&code=" + code +
                "&grant_type=authorization_code";

        // 通过code获取access_token、openid等数据
        Map info = getJsonObjectForWx(url);
        System.out.println("info: " + info);


        url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + info.get("access_token") +
                "&openid=" + info.get("openid");
        Map userInfo = getJsonObjectForWx(url);
        System.out.println("userInfo: " + userInfo);
        String wx_openid = userInfo.get("openid") + "";


        User user = userService.getUserByOpenWxId(wx_openid);
        System.out.println(user);
        // 如果该用户是第一次使用微信登录,把从微信返回的用户信息保存的数据库
        if (user == null) {
            user = new User();
            // 用户的头像
            user.setPic(userInfo.get("headimgurl") + "");
            // 性别
            user.setGender(userInfo.get("sex") + "");
            // 用户的昵称
            String realName=userInfo.get("nickname") + "";
            String replaceName = EmojiUtil.replaceEmoji(realName);
            user.setRealName(replaceName);
            // 随机用户名(11位随机字符串)
            user.setUsername(UUID.randomUUID().toString().substring(36 - 15));
            user.setWxOpenid(wx_openid);
            // 注册一个新的用户
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setRegisterTime(dateFormat.format(new Date()));
            userService.addUser(user);
        }
        System.out.println(user);
        baseSession.setAttribute(Constans.SESSION_LOGIN, user);
        response.sendRedirect("/html/common/home.jsp");


    }



    public Map getJsonObjectForWx(String url) {
        try {
            // 创建一个http Client请求
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            // 获取响应数据(json)
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, Charset.forName("UTF8"));
                return JsonUtils.jsonToPojo(result, HashMap.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
