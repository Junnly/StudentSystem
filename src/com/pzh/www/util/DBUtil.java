package com.pzh.www.util;

import com.pzh.www.constant.MyConstant;

import java.sql.*;

/**
 * 数据库工具类
 * @author Pan梓涵
 * @date 2018/12/4 19:40
 */
public class DBUtil {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败");
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return 返回数据库连接
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(MyConstant.URL, MyConstant.USER, MyConstant.PASSWORD);
        } catch (SQLException e) {
            System.out.println("获取连接失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 执行带占位符的sql语句
     * @param sql sql语句
     * @param params 参数
     * @return 成功返回true,否则返回false
     */
    public static boolean executeUpdate(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            //断言连接不为null
            assert conn != null;
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            return ps.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(ps, conn);
        }
    }

    public static ResultSet executeQuery(Object[] params, PreparedStatement ps) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        return ps.executeQuery();
    }
    
    /**
     * 关闭连接
     * @param ps PreparedStatement
     * @param conn Connection
     */
    private static void close(PreparedStatement ps, Connection conn) {
        close(null, ps, conn);
    }

    /**
     * 关闭连接
     * @param rs ResultSet
     * @param ps PreparedStatement
     * @param conn Connection
     */
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
          if (rs != null) {
              rs.close();
          }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
