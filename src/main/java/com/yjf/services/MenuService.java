package com.yjf.services;

import com.yjf.dao.BaseDao;
import com.yjf.dao.MenuDao;
import com.yjf.entity.Menu;
import com.yjf.utils.DbUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author 余俊锋
 * @date 2020/9/22 17:40
 * @Description
 */
public class MenuService implements MenuDao {

    @Override
    public List<Menu> getMenuAll() {
        String sql="select * from menu";
        System.out.println(jdbcTemplate);
        List<Menu> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Menu.class));
        return list;
    }
}
