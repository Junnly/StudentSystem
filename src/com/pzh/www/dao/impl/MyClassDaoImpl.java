package com.pzh.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pzh.www.dao.MyClassDao;
import com.pzh.www.po.MyClass;
import com.pzh.www.util.DBUtil;

/**
 * 班级业务逻辑接口实现类
 * @author Pan梓涵
 * 创建时间：2018/4/21
 */
public class MyClassDaoImpl implements MyClassDao {

	private List<MyClass> getResultList(ResultSet rs) throws SQLException {
		List<MyClass> list = new ArrayList<>();
		MyClass mc;
		while (rs.next()) {
			mc = new MyClass();
			mc.setClassNo(rs.getInt("class_no"));
			mc.setClassName(rs.getString("class_name"));
			mc.setNumber(rs.getInt("number"));
			list.add(mc);
		}
		return list;
	}

	@Override
	public List<MyClass> findAllClass() {
		String sql = "SELECT * FROM class ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			return getResultList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}

	@Override
	public String getClassNameByClassNo(int classNo) {
		String result = "";
		String sql = "SELECT class_name FROM class WHERE class_no = ? limit 1";
		Integer[] param = {classNo};
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = DBUtil.executeQuery(param, ps);
			if(rs.next()) {
				result = rs.getString("class_name");
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		
	}

	@Override
	public int getClassNoByClassName(String className) {
		int result = -1;
		String sql = "SELECT class_no FROM class WHERE class_name = ?";
		String[] param = {className};
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = DBUtil.executeQuery(param, ps);
			if(rs.next()) {
				result = rs.getInt("class_no");
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}

}
