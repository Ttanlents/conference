package com.yjf.dao;

import com.yjf.entity.Count;
import com.yjf.entity.Page;
import com.yjf.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/9/23 10:03
 * @Description
 */
public class UserDao extends BaseDao {

    /**
     * @param password
     * @return com.yjf.entity.User
     * @Description 查询单个用户
     * @author 余俊锋
     * @date 2020/9/23 10:06
     * @params userName
     */
    public User getUser(String userName, String password) {
        String sql = "select * from `user` where username=? and password=?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getUsers(String username, Page<User> page) {
        String sql = "SELECT\n" +
                "\tu.id,\n" +
                "\tu.username,\n" +
                "\tu.email,\n" +
                "\tu.qq_openid qqOpenid,\n" +
                "\tu.wx_openid wxOpenid,\n" +
                "\tu.real_name,\n" +
                "\tu.age,\n" +
                "\tu.phone,\n" +
                "\tu.`desc`,\n" +
                "\tu.register_time registerTime,\n" +
                "\tu.login_time,\n" +
                "\tu.pic,\n" +
                "\tu.look,\n" +
                "\tu.is_secret isSecret,\n" +
                "\td.`name` deptName,\n" +
                "CASE\n" +
                "\t\tu.gender \n" +
                "\t\tWHEN 1 THEN\n" +
                "\t\t'男' \n" +
                "\t\tWHEN 0 THEN\n" +
                "\t\t'女' ELSE '其他' \n" +
                "\tEND gender \n" +
                "FROM\n" +
                "\t`user` u\n" +
                "\tLEFT JOIN dept d ON u.dept_id = d.id \n" +
                "WHERE\n" +
                "\tu.username LIKE CONCAT( '%', ?, '%' ) \n" +
                "\tLIMIT ?,\n" +
                "\t?;";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username, (page.getPageCurrent() - 1) * page.getPageSize(), page.getPageSize());
        return users;
    }

    public Integer getCount(String username) {
        String sql = "SELECT count(*) count from `user` where username  like CONCAT('%',?,'%')";
        Count count = null;
        try {
            count = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Count.class), username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count.getCount();
    }

    public User getUser(String username) {
        String sql = "select * from `user` where username=?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public int delete(int id) {
        String sql = "delete from user where id=?";
        return jdbcTemplate.update(sql, id);
    }

    public User getUser(int id) {
        try {
            String sql = "SELECT " +
                    "u.id," +
                    "u.username," +
                    "u.email," +
                    "u.real_name realName," +
                    "u.age," +
                    "u.gender," +
                    "u.dept_id deptId," +
                    "u.register_time registerTime " +
                    "FROM" +
                    "`user` u where u.id=?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updata(User user) {
        String sql = "update  `user` set username=?,email=?,real_name=?,age=?,gender=?,dept_id=?,register_time=? where id=?";
        int i = jdbcTemplate.update(sql,
                user.getUsername(), user.getEmail(), user.getRealName(), user.getAge(),
                user.getGender(), user.getDeptId(), user.getRegisterTime(), user.getId());
        return i;
    }

    public int add(User user) {
        String sql = "INSERT INTO `user` ( id, username, email, real_name, age, gender, dept_id, register_time, pic )\n" +
                "VALUES\n" +
                "\t(\n" +
                "\tDEFAULT,?,?,?,?,?,?,?,?)";
        int i = jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getRealName(), user.getAge(), user.getGender(), user.getDeptId(), user.getRegisterTime(), user.getPic());
        return i;
    }

    public int updateImgUrl(String pic, int id) {
        String sql = "update user set pic=? where id=?";
        return jdbcTemplate.update(sql, pic, id);
    }

    public List<Integer> getUsersIdByDeptId(int deptId) {
        String sql = "select id from user where dept_id";
        return jdbcTemplate.queryForList(sql, Integer.class, deptId);
    }
}
