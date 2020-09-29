package com.yjf.controller;

import com.yjf.constans.Constans;
import com.yjf.entity.Dept;
import com.yjf.entity.Page;
import com.yjf.entity.User;
import com.yjf.services.DeptService;
import com.yjf.services.UserService;
import com.yjf.utils.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 余俊锋
 * @date 2020/9/22 14:31
 * @Description
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    DeptService deptService = new DeptService();
    UserService userService = UserService.getInstance();

    public void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
        for (User user : userList) {
           user.setRegisterTime(timeUtil.dateTimetoStr(user.getRegisterTime()));
        }
        req.setAttribute("userList", userList);
        req.setAttribute("username", username);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/html/user/list.jsp").forward(req, res);
    }

    /**
     * @param res
     * @return void
     * @Description 1代表已存在该用户名
     * @author 余俊锋
     * @date 2020/9/23 14:42
     * @params req
     */
    public void checkName(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("name");
        if (username==null){
            username="";
        }
        User user = userService.checkName(username);
        if (user != null) {
            JsonUtils.responseJSON(res, 1);
        } else {
            JsonUtils.responseJSON(res, 0);
        }
    }

    /**
     * @param res
     * @return void
     * @Description id删除用户
     * @author 余俊锋
     * @date 2020/9/23 14:42
     * @params req
     */
    public void deleteUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("id");
        Integer byId = userService.deleteUserById(Integer.parseInt(id));
        if (byId > 0) {
            list(req, res);
        }
    }

    /**
     * @param res
     * @return void
     * @Description TODO:回显用户修改页面
     * @author 余俊锋
     * @date 2020/9/23 14:42
     * @params req
     */
    public void updateUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("id");
        User user = userService.getUserByid(Integer.parseInt(id));


            if (user.getRegisterTime() != null && !user.getRegisterTime().equals("")) {
              String getTime=user.getRegisterTime();
              getTime=getTime.replace(" ","T");
               user.setRegisterTime(getTime);
                System.out.println(getTime);
            }

        req.setAttribute("user", user);
        List<Dept> deptList = deptService.getDeptList();
        req.setAttribute("deptList", deptList);
        req.getRequestDispatcher("/html/user/update.jsp").forward(req, res);
    }

    /**
     * @param res
     * @return void
     * @Description 修改完成，回到用户列表
     * @author 余俊锋
     * @date 2020/9/27 9:32
     * @params req
     */
    public void updateUser2(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.updateUserById(user);
        res.sendRedirect("/user/list");
    }

    /**
     * @param res
     * @return void
     * @Description TODO:添加一个用户
     * @author 余俊锋
     * @date 2020/9/27 9:33
     * @params req
     */
    public void addUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new User();
        Map<String, List<String>> map = upload(req);
        try {
            BeanUtils.populate(user, map);
            String name = user.getUsername();
            System.out.println(URLDecoder.decode(name, "utf-8"));
            System.out.println(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setRegisterTime(dateFormat.format(new Date()));
        user.setWxOpenid(null);
        int i = userService.addUser(user);
        res.sendRedirect("/user/list");
    }
    public void register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new User();
        String password = req.getParameter("password");
        Map<String, List<String>> map = upload(req);
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setRegisterTime(dateFormat.format(new Date()));
        user.setWxOpenid(null);
        user.setPassword(MdUtil.md5(user.getPassword()));
        int i = userService.registerUser(user);
        res.sendRedirect("/index.jsp");
    }

    /**
     * @param res
     * @return void
     * @Description TODO:去用户修改页面，回显部门数据
     * @author 余俊锋
     * @date 2020/9/27 9:33
     * @params req
     */
    public void addUser1(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Dept> deptList = deptService.getDeptList();
        req.setAttribute("deptList", deptList);
        req.getRequestDispatcher("/html/user/add.jsp").forward(req, res);

    }
    public void registerUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Dept> deptList = deptService.getDeptList();
        req.setAttribute("deptList", deptList);
        req.getRequestDispatcher("/html/user/register.jsp").forward(req, res);

    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.util.List   <   java.lang.String>>
     * @Description TODO:接收所有form表单中的数据以及文件，文件存存储到服务器img目录下 。 是一个公共方法
     * @author 余俊锋
     * @date 2020/9/27 9:35
     * @params request
     */
    public Map<String, List<String>> upload(HttpServletRequest request) throws UnsupportedEncodingException {
        String pic = "";
        Map<String, List<String>> map = new HashMap<>();
        //为解析类提供配置信息 创建文件上传工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析类的实例 传入工厂类获取文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        sfu.setHeaderEncoding("ISO8859-1");
        //设置文件最大解析大小(单位：字节)
        sfu.setFileSizeMax(1024 * 1024 * 2);
        //每个表单域中数据会封装到一个对应的FileItem对象上
        try {
            List<FileItem> items = sfu.parseRequest(request);
            for (FileItem item : items) {
                //isFormField为true，表示这不是文件上传表单域
                if (!item.isFormField()) {
                    String fileName = item.getName();
                    String suffix = fileName.substring(fileName.indexOf("."));
                    String realName = UUID.randomUUID().toString().replace("-", "") + suffix;
                    pic = "img/" + realName;
                    String path = this.getClass().getResource("/").getPath();
                    File file = new File(path + pic);
                    ///user/getHeaderPic?Pic=img/a.png
                    if (!file.exists()) {
                        //将文件写出到指定磁盘（即保存图片的服务器）
                       /* int number = userService.updatePic(pic, user.getId());
                        user.setPic(pic);*/
                        item.write(file);//存储上传的图片
                    }
                } else {
                    ArrayList<String> list = new ArrayList<>();
                    String key = item.getFieldName();
                    String value = new String(item.getString().getBytes("ISO8859-1"), "utf-8");
                    if (map.containsKey(key)) {
                        map.get(key).add(value);
                        continue;
                    }
                    list.add(value);
                    map.put(key, list);
                }
                ArrayList<String> list = new ArrayList<>();
                list.add(pic);
                map.put("pic", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @param response
     * @return void
     * @Description TODO:修改头像
     * @author 余俊锋
     * @date 2020/9/27 9:37
     * @params request
     */
    public void updatePic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //为解析类提供配置信息 创建文件上传工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析类的实例 传入工厂类获取文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //设置文件最大解析大小(单位：字节)
        sfu.setFileSizeMax(1024 * 1024 * 2);
        //每个表单域中数据会封装到一个对应的FileItem对象上
        try {
            List<FileItem> items = sfu.parseRequest(request);
            for (int i = 0; i < items.size(); i++) {
                FileItem item = items.get(i);
                //isFormField为true，表示这不是文件上传表单域
                if (!item.isFormField()) {
                    String fileName = item.getName();
                    String suffix = fileName.substring(fileName.indexOf("."));
                    String realName = UUID.randomUUID().toString().replace("-", "") + suffix;
                    String pic = "img/" + realName;
                    String path = this.getClass().getResource("/").getPath();
                    File file = new File(path + pic);
                    ///user/getHeaderPic?Pic=img/a.png
                    if (!file.exists()) {
                        //将文件写出到指定磁盘（即保存图片的服务器）
                        User user = (User) request.getSession().getAttribute(Constans.SESSION_LOGIN);
                        int number = userService.updatePic(pic, user.getId());
                        user.setPic(pic);
                        request.getSession().setAttribute(Constans.SESSION_LOGIN, user);
                        item.write(file);//存储上传的图片
                        /*System.out.println("文件" + file.getAbsolutePath());
                        System.out.println("上传完毕");*/
                        Map<String, String> map = new HashMap<>();
                        map.put("code", "200");
                        if (number > 0) {
                            System.out.println("修改pic成功");
                            map.put("msg", pic);
                        } else {
                            System.out.println("修改失败");
                            map.put("msg", "修改失败！");
                        }

                        JsonUtils.responseJSON(response, map);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param response
     * @return void
     * @Description TODO:显示头像
     * @author 余俊锋
     * @date 2020/9/27 9:38
     * @params request
     */
    public void getHeaderPic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pic = request.getParameter("pic");
        String path = this.getClass().getResource("/").getPath() + pic;
        /* System.out.println("读取头像：" + path);*/
        File file = new File(path);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        OutputStream outputStream = response.getOutputStream();
        int length;
        byte[] flush = new byte[1024];
        while ((length = bis.read(flush)) != -1) {
            outputStream.write(flush, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        bis.close();
    }

    /**
     * @param response
     * @return void
     * @Description TODO:显示验证码图片
     * @author 余俊锋
     * @date 2020/9/27 9:38
     * @params request
     */
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage image = verifyCode.getImage();
        String text = verifyCode.getText();
        System.out.println(text);
        request.getSession().setAttribute(Constans.SESSION_CODE, text);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();
    }

    public void sendEmailCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Map<String,String> map=new HashMap<>();
        String email = request.getParameter("email");
        int code=(new Random().nextInt(1)+1)*1000;
        EmailUtil.sendEmail(email,String.valueOf(code));
        map.put("code","200");
        baseSession.setAttribute(Constans.SESSION_EMAIL,String.valueOf(code));
        JsonUtils.responseJSON(response, map);

    }
    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updatePassword");
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        String emailCode = request.getParameter("emailCode");
        Object o = baseSession.getAttribute(Constans.SESSION_EMAIL);
        if (o==null||!Objects.equals(o.toString(),emailCode)){
            request.getRequestDispatcher("/html/user/forget.jsp").forward(request,response);
            return;
        }
        baseSession.setAttribute(Constans.SESSION_LOGOUT, true);
        String cipherText= MdUtil.md5(newPassword);
        userService.updateUserPasswordByUsername(username,cipherText);
        response.sendRedirect("/index.jsp");
    }






}
