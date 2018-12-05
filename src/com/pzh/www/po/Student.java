package com.pzh.www.po;

/**
 * 学生类 主键(id),姓名 ,性别,紧急联系人电话,邮箱(email),学号,密码
 * 
 * @author Pan梓涵
 *
 */
public class Student {
	private int id;// 主键id
	private String studentName;// 学生姓名
	private String studentGender;// 学生性别
	private String studentPhone;// 紧急联系人电话
	private String studentEmail;// 学生邮箱
	private String studentNo;// 学号
	private String studentPassword;// 密码
	private int classId;// 班级

	public Student() {

	}

	public Student(int id, String studentName, String studentGender, String studentPhone, String studentEmail,
			String studentNo, String studentPassword) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.studentGender = studentGender;
		this.studentPhone = studentPhone;
		this.studentEmail = studentEmail;
		this.studentNo = studentNo;
		this.studentPassword = studentPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + ", studentGender=" + studentGender
				+ ", studentPhone=" + studentPhone + ", studentEmail=" + studentEmail + ", studentNo=" + studentNo
				+ ", studentPassword=" + studentPassword + ", classId=" + classId + "]";
	}
	
	
}
