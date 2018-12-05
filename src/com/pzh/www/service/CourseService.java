package com.pzh.www.service;

import java.util.List;
import java.util.Map;

import com.pzh.www.dao.CourseDao;
import com.pzh.www.dao.impl.CourseDaoImpl;
import com.pzh.www.po.Course;

/**
 * 对课程表的service类
 * @author Pan梓涵
 */
public class CourseService {

	private CourseDao courseDao = new CourseDaoImpl();
	
	public Course getCourseByCourseId(int courseId) {
		return courseDao.getCourseByCourseId(courseId);
	}
	
	public List<Course> getCourseByStudentId(int studentId) {
		return courseDao.getCourseByStudentId(studentId);
	}
	
	public Map<Integer, Course> getAllCourse(){
		return courseDao.getAllCourse();
	}
	
	public Map<Integer,Course> getCourseByTeacherId(int teacherId){
		return courseDao.getCourseByTeacherId(teacherId);
	}
	
	public boolean addCourse(Course course) {
		return courseDao.addCourse(course);
	}
	
	public boolean deleteCourse(int courseId,int giveLensonTeacher) {
		return courseDao.deleteCourse(courseId,giveLensonTeacher);
	}

}
