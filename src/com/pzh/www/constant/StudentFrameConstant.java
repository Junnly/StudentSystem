package com.pzh.www.constant;

import java.awt.Font;

/**
 * 学生视图层用到的常量
 * @author Pan梓涵
 */
public interface StudentFrameConstant {
	
	/**我的信息图片路径*/
	String STUDENT_MAIN_FRAME_MY_MANAGE_IMAGE_PATH = "Images/myManage.png";
	
	/**退出按钮图片路径*/
	String STUDENT_MAIN_FRAME_EXIT_IMAGE_PATH = "Images/exit.png";							

	/**我的课程图片路径*/
	String STUDENT_MAIN_FRAME_MY_COURSE_IMAGE_PATH = "Images/course.png";			

	/**选课报名图片路径*/
	String STUDENT_MAIN_FRAME_SELECT_COURSE_IMAGE_PATH = "Images/selectCourse.png";	
	
	/**我的班级图片路径*/
	String STUDENT_MAIN_FRAME_MY_CLASS_IMAGE_PATH = "Images/myClass.png";			

	/**学生主窗口的我的信息按钮的内容*/
	String STUDENT_MAIN_FRAME_MY_MANAGE_BUTTON = "我的信息";		

	/**学生主窗口的安全退出按钮的内容*/
	String STUDENT_MAIN_FRAME_EXIT_BUTTON = "安全退出";						

	/**学生主窗口的我的课程按钮的内容*/
	String STUDENT_MAIN_FRAME_MY_COURSE_BUTTON = "我的课程";			

	/**学生主窗口的选课报名按钮的内容*/
	String STUDENT_MAIN_FRAME_SELECT_COURSE_BUTTON = "选课报名";	

	/**学生主窗口的我的班级按钮的内容*/
	String STUDENT_MAIN_FRAME_MY_CLASS_BUTTON = "我的班级";			

	/**学生主窗口的按钮字体*/
	Font STUDENT_MAIN_FRAME_BUTTON_FONT = new Font("微软雅黑",Font.PLAIN,14);	

	/**我的班级窗体表格模型的标题栏*/
	String[] MY_CLASS_DIALOG_TABLE_MODEL_COLUMN_NAME = {"姓名","联系电话","邮箱"};																										

	/**我的课程窗体表格模型的标题栏*/
	String[] MY_COURSE_DIALOG_TABLE_MODEL_COLUMN_NAME =
		{"课程编号","课程名字","学时","学分","任课老师","地点","成绩"};													

	/**选课报名窗体表格模型的标题栏*/
	String[] SELECT_COURSE_DIALOG_TABLE_MODEL_COLUMN_NAME =
		{"课程编号","课程名字","学时","学分","教学地点","授课老师","是否选择"};									


	/**我的信息窗体的标签字体*/
	Font MY_MANAGE_FRAME_LABEL_FONT = new Font("微软雅黑",Font.PLAIN,13);

	/**选课报名窗体的按钮字体*/
	Font SELECT_COURSE_DIALOG_BUTTON_FONT = new Font("微软雅黑",Font.BOLD,14);

	/**选课报名窗体：已选课*/
	String SELECT_COURSE_DIALOG_SELECT = "已选";										

	/**选课报名窗体：未选课*/
	String SELECT_COURSE_DIALOG_NOT_SELECT = "未选";							

	/**选课报名窗体的确定选课按钮的内容*/
	String SELECT_COURSE_DIALOG_CONFIRM_BUTTON = "确定选课";			

	/**选课报名窗体的退选按钮的内容*/
	String SELECT_COURSE_DIALOG_CANCEL_BUTTON = "退选";					
	
}
