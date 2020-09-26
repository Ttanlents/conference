package com.yjf.dao;

import com.yjf.utils.DbUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 余俊锋
 * @date 2020/9/22 17:40
 * @Description
 */
public class BaseDao {

   JdbcTemplate jdbcTemplate=new JdbcTemplate(DbUtil.getDataSource());
}
