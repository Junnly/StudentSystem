package com.pzh.www.constant;

import java.awt.Font;

/**
 * 用户视图层用到的常量
 * @author Pan梓涵
 */
public interface UserFrameConstant {
	
	/**用户主窗口背景图片的路径*/
	String USERMAINPANEL_BGIM_PATH = "Images/welcome.jpg";		

	/**用户主窗口表格模型的标题*/
	String[] USER_MAIN_FRAME_TABLEMODEL_COLUMNAME = {"教工编号","老师姓名","管理年级","管理班级","联系电话","邮箱"};

	/**用户主窗口的添加按钮内容*/
	String USER_MAIN_FRAME_ADDBUTTON = "添加";					

	/**用户主窗口的查找按钮内容*/
	String USER_MAIN_FRAME_FINDBUTTON = "查找";					

	/**用户主窗口的首页按钮内容*/
	String USER_MAIN_FRAME_FIRSTPAGE_BUTTON = "首页";		

	/**用户主窗口的尾页按钮内容*/
	String USER_MAIN_FRAME_TRAILERPAGE_BUTTON = "尾页";	

	/**用户主窗口的上一页按钮内容*/
	String USER_MAIN_FRAME_LASTPAGE_BUTTON = "<上一页";	

	/**用户主窗口的下一页按钮内容*/
	String USER_MAIN_FRAME_NEXTPAGE_BUTTON = "下一页 >";

	/**用户主窗口的安全退出按钮内容*/
	String USER_MAIN_FRAME_EXIT_BUTTON = "安全退出";			

	/**用户主窗口的老师列表按钮内容*/
	String USER_MAIN_FRAME_FINDALL_BUTTON = "老师列表/刷新";

	/**用户主窗口的字体*/
	Font USER_MAIN_FRAME_FONT = new Font("微软雅黑", Font.PLAIN, 14);

	/**添加老师窗体的确定按钮内容*/
	String ADD_TEACHER_DIALOG_OK_BUTTON = "确定";				
	
	/**添加老师窗体的取消按钮内容*/
	String ADD_TEACHER_DIALOG_CANCEL_BUTTON = "取消";		

	/**添加老师窗体的取消按钮内容*/
	String ADD_TEACHER_DIALOG_CONTINUEADD_BUTTON = "继续添加";							

	/**添加老师窗体的取消按钮内容*/
	String ADD_TEACHER_DIALOG_BACK_BUTTON = "返回";													

	/**添加老师窗体标签的字体*/
	Font ADD_TEACHER_DIALOG_LABEL_FONT = new Font("微软雅黑",Font.PLAIN,13);			

	/**添加老师窗体按钮的字体*/
	Font ADD_TEACHER_DIALOG_BUTTON_FONT = new Font("微软雅黑", Font.PLAIN, 14);	

	/**查找老师窗体的查找(NO)按钮的内容*/
	String FIND_TEACHER_DIALOG_FIND_BYNO_BUTTON = "查找(No)";				

	/**查找老师窗体的查找(NO)按钮的内容*/
	String FIND_TEACHER_DIALOG_FIND_BYNAME_BUTTON = "查找(name)";	

	/**查找老师窗体的字体*/
	Font FIND_TEACHER_DIALOG_BUTTON_FONT = new Font("微软雅黑", Font.PLAIN, 14);

	/**更新老师窗体的更新按钮*/
	String UPDATE_TEACHER_DIALOG_UPDATE_BUTTON = "保存";											

	/**更新老师窗体的删除按钮*/
	String UPDATE_TEACHER_DIALOG_DELETE_BUTTON = "删除";												

	/**更新老师窗体标签的字体*/
	Font UPDATE_TEACHER_DIALOG_LABEL_FONT = new Font("微软雅黑",Font.PLAIN,13);		

	/**更新老师窗体按钮的字体*/
	Font UPDATE_TEACHER_DIALOG_BUTTON_FONT = new Font("微软雅黑",Font.PLAIN,14);	
}
