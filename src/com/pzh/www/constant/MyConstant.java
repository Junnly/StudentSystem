package com.pzh.www.constant;

/**
 * 工具类和操作类用到的常量
 * @author Pan梓涵
 */
public interface MyConstant {
	
	/**数据库连接用到的常量*/
	String URL = "jdbc:mysql://127.0.0.1:3306/manage_system?useUnicode=true&characterEncoding=UTF-8";
	String USER = "root";
	String PASSWORD = "123456";
	
	/**页面相关的常量*/
	int PAGE_SIZE = 10;
	int PAGE_CURRENT = 1;
	int PAGE_ALL = 0;
	
	/**检查窗体(CheckDialog)的标题*/
	String CHECK_DIALOG_TITLE = "提示";
	
}
