package com.pzh.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pzh.www.dao.SelectCourseDao;
import com.pzh.www.po.SelectCourse;
import com.pzh.www.util.DBUtil;

/**
 * 选课关系接口的实现类
 * @author Pan梓涵
 * 创建时间：2018/4/21
 */
public class SelectCourseDaoImpl implements SelectCourseDao{

	@Override
	public int[] getCourseIdById(int id) {
		//todo
		return null;
	}

	@Override
	public int[] getStudentIdByCourseId(int courseId) {
		//todo
		return null;
	}

	@Override
	public List<SelectCourse> findSelectCourseByStudentId(int studentId) {
		String sql = "SELECT * FROM select_course WHERE id = ? ORDER BY course_id";
		Integer[] param = {studentId};
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = DBUtil.executeQuery(param, ps);
			return getResultList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}
	
	@Override
	public boolean addSelectCourse(SelectCourse sc) {
		String sql = "INSERT INTO select_course (id,course_id) VALUES(?,?)";
		Integer[] params = {sc.getId(),sc.getCourseId()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	@Override
	public Map<Integer, SelectCourse> findSelectCourseByCourseId(int courseId) {
		String sql = "SELECT * FROM select_course WHERE course_id = ?";
		Integer[] params = {courseId};
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = DBUtil.executeQuery(params, ps);
			return getResultMap(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}
	
	@Override
	public boolean setStudentGrade(SelectCourse selectCourse) {
		String sql = "UPDATE select_course SET grade = ? WHERE id = ? AND course_id = ?";
		Integer[] params = {selectCourse.getGrade(),selectCourse.getId(),selectCourse.getCourseId()};
		return DBUtil.executeUpdate(sql, params);
	}

	private List<SelectCourse> getResultList(ResultSet rs) throws SQLException {
		List<SelectCourse> selectCourses = new ArrayList<>();
		SelectCourse selectCourse;
		while (rs.next()) {
			selectCourse = new SelectCourse(rs.getInt("id"),rs.getInt("course_id"),rs.getInt("grade"));
			selectCourses.add(selectCourse);
		}
		return selectCourses;
	}

	private Map<Integer,SelectCourse> getResultMap(ResultSet rs) throws SQLException {
		Map<Integer,SelectCourse> map = new HashMap<>();
		while (rs.next()) {
			SelectCourse sc = new SelectCourse(rs.getInt("id"),rs.getInt("course_id"),rs.getInt("grade"));
			map.put(rs.getInt("id"), sc);
		}
		return map;
	}
	
	private void setMap(ResultSet rs, Map<Integer, SelectCourse> map) throws SQLException {
			SelectCourse sc = new SelectCourse(rs.getInt("id"),rs.getInt("course_id"),rs.getInt("grade"));
			map.put(rs.getInt("id"), sc);
	}


	
}
