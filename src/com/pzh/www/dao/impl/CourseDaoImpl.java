package com.pzh.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pzh.www.dao.CourseDao;
import com.pzh.www.po.Course;
import com.pzh.www.util.DBUtil;

/**
 * @author Pan梓涵
 */
public class CourseDaoImpl implements CourseDao {

	@Override
	public Course getCourseByCourseId(int courseId) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<Course> getCourseByStudentId(int studentId) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Map<Integer, Course> getAllCourse() {
		String sql = "SELECT * FROM course";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			assert conn != null;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			return getResultMap(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}
	
	@Override
	public Map<Integer, Course> getCourseByTeacherId(int teacherId) {
		String sql = "SELECT * FROM course WHERE give_lenson_teacher = ?";
		Integer[] params = {teacherId};
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

	private Map<Integer, Course> getResultMap(ResultSet rs) throws SQLException {
		Map<Integer, Course> courseMap = new HashMap<>();
		Integer courseId;
		Course course;
		while (rs.next()) {
			courseId = rs.getInt("course_id");
			course = new Course();
			course.setCourseId(courseId);
			course.setCourseName(rs.getString("course_name"));
			course.setCourseHour(rs.getInt("course_hour"));
			course.setCredit(rs.getInt("credit"));
			course.setGiveLensonTeacher(rs.getInt("give_lenson_teacher"));
			course.setSite(rs.getString("site"));

			courseMap.put(courseId, course);
		}
		return courseMap;
	}

	@Override
	public boolean addCourse(Course course) {
		String sql = "INSERT INTO course "
				+ "(course_name,course_hour,credit,give_lenson_teacher,site) "
				+ "VALUES(?,?,?,?,?)";
		Object[] params = {
				course.getCourseName(),
				course.getCourseHour(),
				course.getCredit(),
				course.getGiveLensonTeacher(),
				course.getSite()
				};
		return DBUtil.executeUpdate(sql, params);
	}
	
	@Override
	public boolean deleteCourse(int courseId,int giveLensonTeacher) {
		String sql = "DELETE FROM course WHERE course_id = ? AND give_lenson_teacher = ?";
		Integer[] params = {courseId,giveLensonTeacher};
		return DBUtil.executeUpdate(sql, params);
	}

}
