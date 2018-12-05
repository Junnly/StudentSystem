package com.pzh.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pzh.www.dao.TeacherDao;
import com.pzh.www.po.Teacher;
import com.pzh.www.util.DBUtil;

/**
 * 学生业务逻辑接口的实现类
 * @author Pan梓涵
 * 创建时间 2018/4/11 19:17
 */
public class TeacherDaoImpl implements TeacherDao {


	@Override
	public List<Teacher> findAllTeacher() {
		String sql = "SELECT * FROM teachers ORDER BY teacher_no";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			return getTeacherList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}


	@Override
	public List<Teacher> findTeacherByNo(String teacherNo) {
		
		String[] param = {teacherNo};
		String sql = "SELECT * FROM teachers WHERE teacher_no = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = DBUtil.executeQuery(param, ps);
			return getTeacherList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		
	}


	@Override
	public List<Teacher> findTeacherByName(String teacherName) {
		String[] param = {"%" + teacherName + "%"};
		String sql = "SELECT * FROM teachers WHERE teacher_name like ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = DBUtil.executeQuery(param, ps);
			return getTeacherList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}


	@Override
	public boolean addTeacher(Teacher t) {
		String[] param = {
				t.getTeacherName(),
				t.getTeacherGrade(),
				t.getTeacherClass(),
				t.getTeacherPhone(),
				t.getTeacherEmail(),
				t.getTeacherNo(),
				t.getTeacherPassword()
				};
		String sql = "INSERT INTO teachers "
				+ "(teacher_name,teacher_grade,teacher_class,teacher_phone,teacher_email,teacher_no,teacher_password) "
				+ "VALUES(?,?,?,?,?,?,?)";
		return DBUtil.executeUpdate(sql, param);
	}

	@Override
	public boolean updateTeacher(Teacher t) {
		String sql = "UPDATE teachers SET teacher_name = ?,teacher_grade = ?,teacher_class = ?,"
					+ "teacher_phone = ?,teacher_email = ? WHERE teacher_no = ?";
		String[] param = {
				t.getTeacherName(),
				t.getTeacherGrade(),
				t.getTeacherClass(),
				t.getTeacherPhone(),
				t.getTeacherEmail(),
				t.getTeacherNo()
				};
		return DBUtil.executeUpdate(sql, param);
	}

	@Override
	public boolean deleteTeacher(String teacherNo) {
		String[] param = {teacherNo};
		String sql = "DELETE FROM teachers WHERE teacher_no = ?";
		return DBUtil.executeUpdate(sql, param);
	}


	@Override
	public boolean teacherLogin(Teacher t) {
		String[] param = {t.getTeacherNo(),t.getTeacherPassword()};
		String sql = "SELECT * FROM teachers where teacher_no = ? AND teacher_password = ? ";
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

	private Teacher getTeacher(ResultSet rs) throws SQLException {
		Teacher teacher = new Teacher();
		teacher.setId(rs.getInt("id"));
		teacher.setTeacherName(rs.getString("teacher_name"));
		teacher.setTeacherGrade(rs.getString("teacher_grade"));
		teacher.setTeacherClass(rs.getString("teacher_class"));
		teacher.setTeacherPhone(rs.getString("teacher_phone"));
		teacher.setTeacherEmail(rs.getString("teacher_email"));
		teacher.setTeacherNo(rs.getString("teacher_no"));
		teacher.setTeacherPassword(rs.getString("teacher_password"));
		return teacher;
	}

	private List<Teacher> getTeacherList(ResultSet rs) throws SQLException {
		List<Teacher> teachers = new ArrayList<>();
		while (rs.next()) {
			teachers.add(getTeacher(rs));
		}
		return teachers;
	}
	
}
