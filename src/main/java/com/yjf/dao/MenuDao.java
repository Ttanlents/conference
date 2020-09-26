package com.yjf.dao;

import com.yjf.entity.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/9/22 17:34
 * @Description
 */
public class MenuDao extends BaseDao {

    /**
     *@Description TODO
     *@author 余俊锋
     *@date 2020/9/22 17:39
     *@params
     *@return java.util.List<com.yjf.entity.Menu>
     */
   public List<Menu> getMenuAll(){
        String sql="select * from menu";
        List<Menu> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Menu.class));
        return list;
    }


}
