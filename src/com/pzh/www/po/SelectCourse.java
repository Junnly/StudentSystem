package com.pzh.www.po;

/**
 * 选课关系类
 * 
 * @author Pan梓涵 创建时间 2018/4/21
 */
public class SelectCourse {

	private int id;// 与学生表对应的id
	private int courseId;// 与课程表对应的id
	private int grade;

	public SelectCourse() {

	}

	public SelectCourse(int id, int courseId) {
		super();
		this.id = id;
		this.courseId = courseId;
	}
	
	public SelectCourse(int id, int courseId, int grade) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
