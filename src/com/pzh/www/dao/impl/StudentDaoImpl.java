package com.pzh.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pzh.www.dao.StudentDao;
import com.pzh.www.po.Student;
import com.pzh.www.util.DBUtil;

/**
 * @author Pan梓涵
 */
public class StudentDaoImpl implements StudentDao {

	@Override
	public List<Student> findAllStudent() {
		String sql = "SELECT * FROM students ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			return getStudentList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		
	}

	@Override
	public Map<Integer, Student> findAllStudentMap() {
		String sql = "SELECT * FROM students ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			return getStudentMap(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}


	@Override
	public List<Student> findStudentByNo(String studentNo) {
		String sql = "SELECT * FROM students WHERE student_no = ?";
		String[] param = {studentNo};
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = DBUtil.executeQuery(param, ps);
			return getStudentList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}


	@Override
	public List<Student> findStudentByClassNo(int classNo) {
		String sql = "SELECT * FROM students WHERE class_id = ?";
		Integer[] param = {classNo};
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = DBUtil.executeQuery(param, ps);
			return getStudentList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}

	@Override
	public List<Student> findStudentByName(String studentName) {
		String sql = "select * from students where student_name like ?";
		String[] param = {"%" + studentName + "%"};
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = DBUtil.executeQuery(param, ps);
			return getStudentList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}


	@Override
	public boolean addStudent(Student s) {
		Object[] params = {
				s.getStudentName(),s.getStudentGender(),s.getStudentPhone(),
				s.getStudentEmail(),s.getStudentNo(),s.getStudentPassword(),
				s.getClassId()};
		String sql = "INSERT INTO students "
				+ "(student_name,student_gender,student_phone,student_email,student_no,student_password,class_id) "
				+ "VALUES(?,?,?,?,?,?,?)";
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public boolean updateStudent(Student s) {
		String[] param = {
				s.getStudentName(),
				s.getStudentGender(),
				s.getStudentPhone(),
				s.getStudentEmail(),
				s.getClassId() + "",
				s.getStudentNo()
				};
		String sql = "UPDATE students SET student_name = ?,student_gender = ?,student_phone = ?,"
					+ "student_email = ?,class_id = ?  WHERE student_no = ?";
		return DBUtil.executeUpdate(sql, param);
	}

	@Override
	public boolean deleteStudent(String studentNo) {
		String[] param = {studentNo};
		String sql = "DELETE FROM students WHERE student_no = ?";
		return DBUtil.executeUpdate(sql, param);
	}


	@Override
	public boolean studentLogin(Student s) {
		
		String sql = "SELECT * FROM students WHERE student_no = ? AND student_password = ? ";
		String[] param = {s.getStudentNo(),s.getStudentPassword()};
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

	private Student getStudent(ResultSet rs) throws SQLException {
		Student student = new Student();
		student.setId(rs.getInt("id"));
		student.setStudentName(rs.getString("student_name"));
		student.setStudentGender(rs.getString("student_gender"));
		student.setStudentPhone(rs.getString("student_phone"));
		student.setStudentEmail(rs.getString("student_email"));
		student.setStudentNo(rs.getString("student_no"));
		student.setStudentPassword(rs.getString("student_password"));
		student.setClassId(rs.getInt("class_id"));
		return student;
	}

	private List<Student> getStudentList(ResultSet rs) throws SQLException {
		List<Student> students = new ArrayList<>();
		while (rs.next()) {
			students.add(getStudent(rs));
		}
		return students;
	}

	private Map<Integer, Student> getStudentMap(ResultSet rs) throws SQLException {
		Map<Integer, Student> studentMap = new HashMap<>();
		while (rs.next()) {
			Student student = getStudent(rs);
			studentMap.put(student.getId(), student);
		}
		return studentMap;
	}
}
