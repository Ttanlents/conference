package com.yjf.services;

import com.sun.org.glassfish.gmbal.Description;
import com.sun.xml.internal.bind.v2.TODO;
import com.yjf.dao.UserDao;
import com.yjf.entity.Page;
import com.yjf.entity.User;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/9/23 10:14
 * @Description
 */
public class UserService {

    UserDao userDao = new UserDao();

    /**
     * 饿汉式
     */
    private static final UserService instance = new UserService();

    /**
     * @return com.yjf.services.UserService
     * @Description 单例userService
     * @author 余俊锋
     * @date 2020/9/23 15:29
     * @params
     */
    public static UserService getInstance() {
        return instance;
    }


    /**
     * @return
     * @Description 校验用户名是否存在
     * @author 余俊锋
     * @date 2020/9/23 17:42
     * @params null
     */
    public User checkName(String username) {
        return userDao.getUser(username);
    }


    /**
     * @param password
     * @return com.yjf.entity.User
     * @Description 通过账号密码登陆
     * @author 余俊锋
     * @date 2020/9/23 17:42
     * @params username
     */
    public User login(String username, String password) {
        return userDao.getUser(username, password);
    }


    /**
     * @param page
     * @return java.util.List<com.yjf.entity.User>
     * @Description 分页查询
     * @author 余俊锋
     * @date 2020/9/23 17:42
     * @params username
     */
    public List<User> getUserList(String username, Page<User> page) {
        return userDao.getUsers(username, page);
    }

    /**
     * @return java.lang.Integer
     * @Description 查询数据库总记录数
     * @author 余俊锋
     * @date 2020/9/23 17:41
     * @params
     */
    public Integer getCount(String username) {
        return userDao.getCount(username);
    }

    /**
     * @return java.lang.Integer
     * @Description 通过id删除用户
     * @author 余俊锋
     * @date 2020/9/23 17:41
     * @params id
     */
    public Integer deleteUserById(Integer id) {
        return userDao.delete(id);
    }

    public Integer updateUserById(User user) {
        return userDao.updata(user);
    }

    public User  getUserByid(int id){
        return userDao.getUser(id);
    }

    public int addUser(User user){
     return    userDao.add(user);
    }
    public int registerUser(User user){
        return    userDao.register(user);
    }

    public int updatePic(String pic,int id){
      return   userDao.updateImgUrl(pic,id);
    }

    public void updateUserPasswordByUsername(String username,String password){
       userDao.updatePasswordByUsername(username,password);
    }

    public User getUserByOpenWxId(String wx_openid){
       return userDao.getUserByOpenWxId(wx_openid);
    }


}
