package com.pzh.www.service;

import java.util.List;

import com.pzh.www.dao.MyClassDao;
import com.pzh.www.dao.impl.MyClassDaoImpl;
import com.pzh.www.po.MyClass;

/**
 * 对班级表的service类
 * @author Pan梓涵
 * 创建时间：2018/4/21
 */
public class MyClassService {

	private static final MyClassService INSTANCE = new MyClassService();

	private MyClassService() {}

	public static MyClassService getInstance() {
		return INSTANCE;
	}
	
	private MyClassDao myClassDao = new MyClassDaoImpl();

	/**
	 * 查找所有班级资料
	 */
	public List<MyClass> findAllClass(){
		return myClassDao.findAllClass();
	}
	
	/**
	 * 根据班级编号,查找班级名字
	 */
	public String getClassNameByClassNo(int classNo) {
		return myClassDao.getClassNameByClassNo(classNo);
	}
	
	/**
	 * 根据班级名字,查找班级编号
	 */
	public int getClassNoByClassName(String className) {
		return myClassDao.getClassNoByClassName(className);
	}
}
