package com.pzh.www.po;

/**
 * 老师类
 * 主键（id）,姓名 ,年级，班级, 电话，邮箱，教工编号，密码
 * @author Pan梓涵
 */
public class Teacher {
	private int id;// 主键id
	private String teacherName;// 老师姓名
	private String teacherGrade;// 老师年级
	private String teacherClass;// 老师班级
	private String teacherPhone;// 老师电话
	private String teacherEmail;// 老师邮箱
	private String teacherNo;	// 老师编号
	private String teacherPassword;// 老师密码

	public Teacher() {

	}

	public Teacher(int id, String teacherName, String teacherGrade, String teacherClass, String teacherPhone,
			String teacherEmail, String teacherNo, String teacherPassword) {
		super();
		this.id = id;
		this.teacherName = teacherName;
		this.teacherGrade = teacherGrade;
		this.teacherClass = teacherClass;
		this.teacherPhone = teacherPhone;
		this.teacherEmail = teacherEmail;
		this.teacherNo = teacherNo;
		this.teacherPassword = teacherPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherGrade() {
		return teacherGrade;
	}

	public void setTeacherGrade(String teacherGrade) {
		this.teacherGrade = teacherGrade;
	}

	public String getTeacherClass() {
		return teacherClass;
	}

	public void setTeacherClass(String teacherClass) {
		this.teacherClass = teacherClass;
	}

	public String getTeacherPhone() {
		return teacherPhone;
	}

	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getTeacherPassword() {
		return teacherPassword;
	}

	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}
	
	

}
