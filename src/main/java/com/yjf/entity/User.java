package com.yjf.entity;

import java.util.Objects;

/**
 * @author 余俊锋
 * @date 2020/9/22 10:30
 * @Description 用户实体类
 */
public class User extends BaseEntity {
    /**
     *主键Id
     */
    private Integer id;

    /**
     *用户名
     */
    private String username;

    /**
     *用户密码
     */
    private String password;

    /**
     *用户邮箱
     */
    private String email;

    /**
     *QQ登录标识
     */
    private String qqOpenid;

    /**
     *主键Id
     */
    private String wxOpenid;

    /**
     *真实姓名
     */
    private String realName;

    /**
     *年龄
     */
    private Integer age;

    /**
     *电话号码
     */
    private String phone;

    /**
     *性别
     */
    private String gender;

    /**
     *简介
     */
    private String desc;

    /**
     *注册时间
     */
    private String registerTime;

    /**
     *上次登录时间
     */
    private String loginTime;

    /**
     *头像（地址）
     */
    private String pic;

    /**
     *查看次数
     */
    private Integer look;

    /**
     *是否私密
     */
    private String isSecret;

    /**
     *部门名称
     */
    private String deptName;

    /**
     *部门id
     */
    private Integer deptId;

    public User() {
    }

    public User(Integer id, String username, String password, String email, String qqOpenid, String wxOpenid, String realName, Integer age, String phone, String gender, String desc, String registerTime, String loginTime, String pic, Integer look, String isSecret, String deptName, Integer deptId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.qqOpenid = qqOpenid;
        this.wxOpenid = wxOpenid;
        this.realName = realName;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
        this.desc = desc;
        this.registerTime = registerTime;
        this.loginTime = loginTime;
        this.pic = pic;
        this.look = look;
        this.isSecret = isSecret;
        this.deptName = deptName;
        this.deptId = deptId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getLook() {
        return look;
    }

    public void setLook(Integer look) {
        this.look = look;
    }

    public String getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(String isSecret) {
        this.isSecret = isSecret;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(qqOpenid, user.qqOpenid) &&
                Objects.equals(wxOpenid, user.wxOpenid) &&
                Objects.equals(realName, user.realName) &&
                Objects.equals(age, user.age) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(desc, user.desc) &&
                Objects.equals(registerTime, user.registerTime) &&
                Objects.equals(loginTime, user.loginTime) &&
                Objects.equals(pic, user.pic) &&
                Objects.equals(look, user.look) &&
                Objects.equals(isSecret, user.isSecret) &&
                Objects.equals(deptName, user.deptName) &&
                Objects.equals(deptId, user.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, qqOpenid, wxOpenid, realName, age, phone, gender, desc, registerTime, loginTime, pic, look, isSecret, deptName, deptId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", qqOpenid='" + qqOpenid + '\'' +
                ", wxOpenid='" + wxOpenid + '\'' +
                ", realName='" + realName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", desc='" + desc + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", pic='" + pic + '\'' +
                ", look=" + look +
                ", isSecret='" + isSecret + '\'' +
                ", deptName='" + deptName + '\'' +
                ", deptId=" + deptId +
                '}';
    }
}
