package com.vote.dao;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class dbUtil {

    // 读取配置文件
    private static Properties config=new Properties();
    static{
        try {
            //配置文件
            config.load(dbUtil.class.getClassLoader().getResourceAsStream("com/vote/config/db.properties"));
            //加载驱动
            Class.forName(config.getProperty("driver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库
     */
    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(config.getProperty("url"),config.getProperty("username"),config.getProperty("password"));
        return con;
    }

    /**
     * 关闭数据库
     */
    public static void closeAll(ResultSet rs,Statement stmt,Connection con) throws SQLException{
        if(rs!=null)
            rs.close();
        if(stmt!=null)
            stmt.close();
        if(con!=null)
            con.close();
    }

}
