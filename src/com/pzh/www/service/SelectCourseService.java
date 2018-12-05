package com.pzh.www.service;

import java.util.List;
import java.util.Map;

import com.pzh.www.dao.SelectCourseDao;
import com.pzh.www.dao.impl.SelectCourseDaoImpl;
import com.pzh.www.po.SelectCourse;

/**
 * 对选课关系表的service类
 * @author Pan梓涵
 */
public class SelectCourseService {

	private SelectCourseDao selectCourseDao = new SelectCourseDaoImpl();

	public int[] getCourseIdById(int id) {
		return selectCourseDao.getCourseIdById(id);
	}
	
	public int[] getStudentIdByCourseId(int courseId) {
		return selectCourseDao.getStudentIdByCourseId(courseId);
	}
	
	public List<SelectCourse> findSelectCourseByStudentId(int studentId){
		return selectCourseDao.findSelectCourseByStudentId(studentId);
	}
	
	public boolean addSelectCourse(int id, int courseId) {
		return selectCourseDao.addSelectCourse(new SelectCourse(id, courseId));
	}
	
	public Map<Integer, SelectCourse> findSelectCourseByCourseId(int courseId){
		SelectCourseDao scdao = new SelectCourseDaoImpl();
		return selectCourseDao.findSelectCourseByCourseId(courseId);
	}
	
	public boolean setStudentGrade(int id,int courseId,int grade) {
		return selectCourseDao.setStudentGrade(new SelectCourse(id, courseId, grade));
	}
}
