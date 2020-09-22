package com.yjf.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.yjf.entity.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author admin
 * @date 2020/9/22
 * @Description jdbcTemplate连接工具类
 */
public class DbUtil {

    private static DruidDataSource druidDataSource;

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        System.out.println("=======测试jdbcTemplate:" + jdbcTemplate);
    }

    static {
        Properties prop = new Properties();
        InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            prop.load(in);
            druidDataSource = new DruidDataSource();
            druidDataSource.setUsername(prop.getProperty("username"));
            druidDataSource.setPassword(prop.getProperty("password"));
            druidDataSource.setUrl(prop.getProperty("url"));
            druidDataSource.setDriverClassName(prop.getProperty("driver"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据源
     */
    public static DruidDataSource getDataSource() {
        return druidDataSource;
    }

}
