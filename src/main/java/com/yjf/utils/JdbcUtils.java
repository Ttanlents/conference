package com.yjf.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 余俊锋
 * @date 2020/9/8 17:33
 */
public class JdbcUtils {
    public static void main(String[] args) {
        Connection conn = JdbcUtils.getConn();
        System.out.println("数据库连接成功---->"+conn);
    }
    static String DRIVER;
    static String URL;
    static String USERNAME;
    static String PASSWORD;

    static {
        Properties properties = new Properties();
        FileInputStream fis=null;
        try {
            //JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties")
        /* String sss=JdbcUtils.class.getResource("/").getPath() + "jdbc.properties";*/
             fis = new FileInputStream(JdbcUtils.class.getResource("/").getPath() + "/db.properties");
            properties.load(fis);
            DRIVER = properties.getProperty("driver");
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           if (fis!=null){
               try {
                   fis.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }

    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(AutoCloseable ...a){
        for (AutoCloseable closeable : a) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * dml一条数据记录
     * @param sql
     * @param obj
     */
    public static void  dml(String sql,Object ...obj){
        if (sql==null||sql.equals("")||obj==null||obj.length<=0){
            return;
        }
        Connection conn = JdbcUtils.getConn();
        PreparedStatement ps=null;
        try {
           ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
            int i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(ps,conn);
        }
    }


}
