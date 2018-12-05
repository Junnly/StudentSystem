package com.pzh.www.dao;

import java.util.List;
import java.util.Map;

import com.pzh.www.po.Course;

/**
 * 课程类的业务逻辑接口
 * @author Pan梓涵
 * 创建时间：2018/4/21
 */
 public interface CourseDao {

	/**
	 * 根据课程id获取课程信息
	 * @param courseId 课程id
	 * @return 课程信息
	 */
	Course getCourseByCourseId(int courseId);

	/**
	 * 根据学生id获取课程信息
	 * @param studentId 学生id
	 * @return 课程信息
	 */
	List<Course> getCourseByStudentId(int studentId);

	/**
	 * 获取全部课程信息
	 * @return 全部课程信息
	 */
	Map<Integer,Course> getAllCourse();

	/**
	 * 根据老师id获取课程信息
	 * @param teacherId 老师id
	 * @return 课程信息
	 */
	Map<Integer,Course> getCourseByTeacherId(int teacherId);

	/**
	 * 添加课程
	 * @param course 课程信息
	 * @return 成功返回true,否则为false
	 */
	boolean addCourse(Course course);

	/**
	 * 删除课程
	 * @param courseId 课程id
	 * @param teacherId 老师id
	 * @return 成功返回true,否则为false
	 */
	boolean deleteCourse(int courseId,int teacherId);
}
