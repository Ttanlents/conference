package com.yjf.dao;

import com.yjf.entity.Dept;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/9/23 19:34
 * @Description
 */
public class DeptDao extends BaseDao {
    public List<Dept> getAllDept() {
        String sql = "select * from dept";
        List<Dept> deptList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dept.class));
        return deptList;
    }
}
