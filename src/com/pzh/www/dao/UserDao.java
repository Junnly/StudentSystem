package com.pzh.www.dao;

import com.pzh.www.po.User;


/**
 * 用户业务逻辑接口
 * @author Pan梓涵
 */
public interface UserDao {

	/**
	 * 用户登录方法
	 * @param u
	 * @return
	 */
	boolean usersLogin(User u);
}
