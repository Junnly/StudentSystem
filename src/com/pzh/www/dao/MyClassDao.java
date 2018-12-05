package com.pzh.www.dao;

import java.util.List;

import com.pzh.www.po.MyClass;

/**
 * 班级业务逻辑接口
 * @author Pan梓涵
 * 创建时间：2018/4/21
 */
public interface MyClassDao {

	/**
	 * 获取全部班级资料
	 * @return 班级资料
	 */
	List<MyClass> findAllClass();

	/**
	 * 根据班级编号，获取班级名称
	 * @param classNo 班级编号
	 * @return 班级名称
	 */
	String getClassNameByClassNo(int classNo);
	
	/**
	 * 根据班级名称，获取班级编号
	 * @param className 班级名称
	 * @return 班级编号
	 */
	int getClassNoByClassName(String className);
}
