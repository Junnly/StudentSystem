package com.pzh.www.po;

/**
 * 班级实体类
 * 
 * @author Pan梓涵 班级编号,班级名字,班级人数
 */
public class MyClass {

	private int classNo;// 班级编号
	private String className;// 班级名称
	private int number;// 班级人数

	public MyClass() {
		super();
	}

	public MyClass(int classNo, String className, int number) {
		super();
		this.classNo = classNo;
		this.className = className;
		this.number = number;
	}

	public int getClassNo() {
		return classNo;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
