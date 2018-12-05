package com.pzh.www.constant;

import java.awt.Font;

/**
 * 老师视图层用到的常量
 *
 * @author Pan梓涵
 */
public interface TeacherFrameConstant {

    /**老师主窗口背景图片的路径*/
    String TEACHER_MAIN_FRAME_BGIM_PATH = "Images/welcome.jpg";            

    /**老师主窗口的表格模型的标题栏*/
    String[] TEACHER_MAIN_FRAME_TABLEMODEL_COLUMNNAME =
            {"学号", "学生姓名", "性别", "紧急联系人电话", "邮箱(email)", "班级"};                                                    


    /**老师主窗口的添加按钮内容*/
    String TEACHER_MAIN_FRAME_ADD_BUTTON = "添加";                                

    /**老师主窗口的添加按钮内容*/
    String TEACHER_MAIN_FRAME_FIND_BUTTON = "查找";                                

    /**老师主窗口的安全退出按钮内容*/
    String TEACHER_MAIN_FRAME_EXIT_BUTTON = "安全退出";                            

    /**老师主窗口的教授课程按钮内容*/
    String TEACHER_MAIN_FRAME_TEACH_COURSE_BUTTON = "教授课程";        

    /**老师主窗口的教授课程按钮内容*/
    String TEACHER_MAIN_FRAME_FINDALL_STUDENT_BUTTON = "学生列表/刷新";    

    /**老师主窗口的首页按钮内容*/
    String TEACHER_MAIN_FRAME_FIRST_PAGE_BUTTON = "首页";                    

    /**老师主窗口的尾页按钮内容*/
    String TEACHER_MAIN_FRAME_TRAILER_PAGE_BUTTON = "尾页";                

    /**老师主窗口的上一页按钮内容*/
    String TEACHER_MAIN_FRAME_LAST_PAGE_BUTTON = "<上一页";                

    /**老师主窗口的下一页按钮内容*/
    String TEACHER_MAIN_FRAME_NEXT_PAGE_BUTTON = "下一页 >";                

    /**老师主窗口的按钮字体*/
    Font TEACHER_MAIN_FRAME_BUTTON_FONT = new Font("微软雅黑", Font.PLAIN, 13);    

    /**老师主窗口的面板字体*/
    Font TEACHER_MAIN_FRAME_PANEL_FONT = new Font("微软雅黑", Font.PLAIN, 14);        

    /**添加学生窗体的确定按钮的内容*/
    String ADD_STUDENT_DIALOG_OK_BUTTON = "确定";                                    

    /**添加学生窗体的取消按钮的内容*/
    String ADD_STUDENT_DIALOG_CANCEL_BUTTON = "取消";                            

    /**添加学生窗体的继续添加按钮的内容*/
    String ADD_STUDENT_DIALOG_CONTINUE_ADD_BUTTON = "继续添加";        

    /**添加学生窗体的继续返回按钮的内容*/
    String ADD_STUDENT_DIALOG_BACK_BUTTON = "返回";                                

    /**添加学生窗体的按钮字体*/
    Font ADD_STUDENT_DIALOG_BUTTON_FONT = new Font("微软雅黑", Font.PLAIN, 14);    

    /**添加学生窗体的标签字体*/
    Font ADD_STUDENT_DIALOG_LABEL_FONT = new Font("微软雅黑", Font.PLAIN, 13);        

    /**查找学生窗体的查找(NO)按钮内容*/
    String FIND_STUDENT_DIALOG_FIND_BYNO_BUTTON = "查找(No)";            

    /**查找学生窗体的查找(NAME)按钮内容*/
    String FIND_STUDENT_DIALOG_FIND_BYNAME_BUTTON = "查找(name)";    

    /**查找学生窗体的标签字体*/
    Font FIND_STUDENT_DIALOG_LABEL_FONT = new Font("微软雅黑", Font.PLAIN, 13);        

    /**查找学生窗体的按钮字体*/
    Font FIND_STUDENT_DIALOG_BUTTON_FONT = new Font("微软雅黑", Font.PLAIN, 14);    

    /**更新学生窗体的保存按钮内容*/
    String UPDATE_STUDENT_DIALOG_UPDATE_BUTTON = "保存";                                      

    /**更新学生窗体的删除按钮内容*/
    String UPDATE_STUDENT_DIALOG_DELETE_BUTTON = "删除";                                          

    /**更新学生窗体的按钮字体*/
    Font UPDATE_STUDENT_DIALOG_BUTTON_FONT = new Font("微软雅黑", Font.PLAIN, 14);

    /**更新学生窗体的标签字体*/
    Font UPDATE_STUDENT_DIALOG_LABEL_FONT = new Font("微软雅黑", Font.PLAIN, 13); 

    /**我的教授课程窗体的表格模型的标题栏*/
    String[] MYTEACH_COURSE_DIALOG_TABLEMODEL_COLUMNNAME =
            {"课程编号", "课程名字", "学时", "学分", "地点"};                                                                                

    /**我的教授课程窗题的添加按钮的内容*/
    String MYTEACH_COURSE_DIALOG_ADD_COURSE_BUTTON = "添加课程";    

    /**我的教授课程窗题的删除按钮的内容*/
    String MYTEACH_COURSE_DIALOG_DELETE_COURSE_BUTTON = "删除课程";

    /**教授课程窗体的标签字体*/
    Font MYTEACH_COURSE_DIALOG_LABEL_FONT = new Font("微软雅黑", Font.PLAIN, 13);

    /**设置成绩窗体表格模型的标题栏	*/
    String[] SET_STUDENT_COURSE_GRADE_TABLEMODEL_COLUMNNAME =
            {"学生id", "学生学号", "名字", "成绩"};                                                                                                

    /**设置成绩窗体的保存成绩按钮的内容*/
    String SET_STUDENT_COURSE_GRADE_UPDATE_BUTTON = "保存成绩";        

    /**设置成绩窗体标签字体*/
    Font SET_STUDENT_COURSE_GRADE_LABEL_FONT = new Font("微软雅黑", Font.PLAIN, 13);
}
