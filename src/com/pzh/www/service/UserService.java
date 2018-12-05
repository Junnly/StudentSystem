package com.pzh.www.service;

import com.pzh.www.dao.UserDao;
import com.pzh.www.po.User;
import com.pzh.www.dao.impl.UserDaoImpl;

/**
 * 作用：用户的一些基本操作
 * @author Pan梓涵 
 * 创建时间： 2018-4-10
 */
public class UserService {

	private static final UserService INSTANCE = new UserService();

	private UserService() {}

	public static UserService getInstance() {
		return INSTANCE;
	}

	private UserDao userDao = new UserDaoImpl();

	/**
	 * 用户登录动作
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username,String password) {
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		return userDao.usersLogin(user);
	}
	
}
