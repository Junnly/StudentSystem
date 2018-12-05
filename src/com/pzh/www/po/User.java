package com.pzh.www.po;
/**
 * 用户类
 * 主键（id）,用户名 ,密码
 * @author Pan梓涵
 */
public class User {
	private int id;				//主键id
	private String userName;	//用户名
	private String password;	//密码

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
