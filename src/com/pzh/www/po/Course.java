package com.pzh.www.po;

/**
 * 课程类
 * @author Pan梓涵
 * 创建时间 2018/4/21
 */
public class Course {
	
	private int courseId;//课程id
	private String courseName;//课程名字
	private int courseHour;//学时
	private int credit;//学分
	private int giveLensonTeacher;//授课老师
	private String site;//上课地点
	
	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public int getCourseHour() {
		return courseHour;
	}
	
	public void setCourseHour(int courseHour) {
		this.courseHour = courseHour;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public int getGiveLensonTeacher() {
		return giveLensonTeacher;
	}

	public void setGiveLensonTeacher(int giveLensonTeacher) {
		this.giveLensonTeacher = giveLensonTeacher;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Course() {
		
	}

	public Course(int courseId, String courseName, int courseHour, int credit) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseHour = courseHour;
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseHour=" + courseHour
				+ ", credit=" + credit + "]";
	}
	
	
	
}
