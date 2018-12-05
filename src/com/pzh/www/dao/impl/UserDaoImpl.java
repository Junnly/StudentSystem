package com.pzh.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pzh.www.dao.UserDao;
import com.pzh.www.po.User;
import com.pzh.www.util.DBUtil;

/**
 * 用户业务逻辑接口的实现类
 * @author Pan梓涵
 * 修改时间 2018/4/14 14:07
 */
public class UserDaoImpl implements UserDao {

	@Override
	public boolean usersLogin(User u) {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ? ";
		String[] param = {u.getUserName(),u.getPassword()};
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = DBUtil.executeQuery(param, ps);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}
	
	

}
