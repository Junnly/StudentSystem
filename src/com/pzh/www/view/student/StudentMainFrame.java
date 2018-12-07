package com.pzh.www.view.student;

import com.pzh.www.service.CourseService;
import com.pzh.www.service.SelectCourseService;
import com.pzh.www.constant.StudentFrameConstant;
import com.pzh.www.po.Course;
import com.pzh.www.po.SelectCourse;
import com.pzh.www.po.Student;
import com.pzh.www.po.Teacher;
import com.pzh.www.service.StudentService;
import com.pzh.www.service.TeacherService;
import com.pzh.www.view.login.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生界面主窗体
 * @author Pan梓涵
 */
public class StudentMainFrame extends JFrame {

	private static final long serialVersionUID = 7581064105144047315L;
	private JPanel centrePanel;// 中心面板
	private JButton myManageButton;// 我的信息按钮
	private JButton exitButton;// 安全退出按钮
	private JButton myCourseButton;// 我的课程按钮
	private JButton selectCourseButton;// 选课按钮
	private JButton myClassButton;// 我的班级按钮
	private String studentNo;// 学号
	private Student student;//	学生对象
	private StudentService studentService;// 学生操作类
	private SelectCourseService selectCourseService;// 选课操作类
	private CourseService courseService;// 课程操作类
	private Map<Integer,Course> courseMap;// 所有课程Map
	private List<SelectCourse> selectCourseList;// 选课List
	private TeacherService teacherService;// 老师操作类
	private Map<Integer,Teacher> teacherMap;// 老师资料Map
	
	public StudentMainFrame(String studentNo) {
		this.studentNo = studentNo;
		this.init();
		this.initService();
		this.student = studentService.findStudentByNo(studentNo).get(0);
		this.courseMap = courseService.getAllCourse();
		this.selectCourseList = selectCourseService.findSelectCourseByStudentId(student.getId());
		this.setVisible(true);
		this.setResizable(false);
	}
	
	private void initService() {
		this.studentService = StudentService.getInstance();
		this.selectCourseService = SelectCourseService.getInstance();
		this.courseService = CourseService.getInstance();
		this.teacherService = TeacherService.getInstance();
		List<Teacher> teacherList = teacherService.findAllTeacher();
		this.teacherMap = new HashMap<>();
		for (Teacher teacher : teacherList) {
			teacherMap.put(teacher.getId(), teacher);
		}
	}

	private void initMyManageButton() {
		myManageButton = new JButton(StudentFrameConstant.STUDENT_MAIN_FRAME_MY_MANAGE_BUTTON);
		myManageButton.setSize(150, 100);
		myManageButton.setLocation(0, 10);
		myManageButton.setFont(StudentFrameConstant.STUDENT_MAIN_FRAME_BUTTON_FONT);
		myManageButton.setBackground(Color.green);
		myManageButton.addActionListener(e -> new MyManageFrame(student));
	}

	private void initExitButton() {
		exitButton = new JButton(StudentFrameConstant.STUDENT_MAIN_FRAME_EXIT_BUTTON);
		exitButton.setFont(StudentFrameConstant.STUDENT_MAIN_FRAME_BUTTON_FONT);
		exitButton.setSize(130, 60);
		exitButton.setLocation(100, 260);
		exitButton.setBackground(Color.RED);
		exitButton.addActionListener(e -> {
			if(studentNo != null) {
				getStudentMainFrame().setVisible(false);
				getStudentMainFrame().dispose();
				new LoginFrame();
			}
		});
	}

	private void initMyCourseButton() {
		myCourseButton = new JButton(StudentFrameConstant.STUDENT_MAIN_FRAME_MY_COURSE_BUTTON);
		myCourseButton.setFont(StudentFrameConstant.STUDENT_MAIN_FRAME_BUTTON_FONT);
		myCourseButton.setBackground(Color.MAGENTA);
		myCourseButton.setSize(140, 80);
		myCourseButton.setLocation(155, 15);
		myCourseButton.addActionListener(e -> new MyCourseDialog(getStudentMainFrame(), student).setVisible(true));
	}

	private void initSelectCourseButton() {
		selectCourseButton = new JButton(StudentFrameConstant.STUDENT_MAIN_FRAME_SELECT_COURSE_BUTTON);
		selectCourseButton.setBackground(Color.LIGHT_GRAY);
		selectCourseButton.setSize(140, 80);
		selectCourseButton.setLocation(0, 165);
		selectCourseButton.setFont(StudentFrameConstant.STUDENT_MAIN_FRAME_BUTTON_FONT);
		selectCourseButton.addActionListener(e -> {
			new SelectCourseDialog(getStudentMainFrame(), student).setVisible(true);;
		});
	}

	private void initMyClassButton() {
		myClassButton = new JButton(StudentFrameConstant.STUDENT_MAIN_FRAME_MY_CLASS_BUTTON);
		myClassButton.setSize(140, 80);
		myClassButton.setLocation(150, 130);
		myClassButton.setBackground(Color.PINK);
		myClassButton.setFont(StudentFrameConstant.STUDENT_MAIN_FRAME_BUTTON_FONT);
		myClassButton.addActionListener(e -> {
			new MyClassDialog(getStudentMainFrame(),student).setVisible(true);;
		});
	}
	
	private void initButton() {
		this.initMyManageButton();
		this.initExitButton();
		this.initMyCourseButton();
		this.initSelectCourseButton();
		this.initMyClassButton();
		
		setIcon(StudentFrameConstant.STUDENT_MAIN_FRAME_MY_MANAGE_IMAGE_PATH, myManageButton);
		setIcon(StudentFrameConstant.STUDENT_MAIN_FRAME_MY_CLASS_IMAGE_PATH, myClassButton);
		setIcon(StudentFrameConstant.STUDENT_MAIN_FRAME_EXIT_IMAGE_PATH, exitButton);
		setIcon(StudentFrameConstant.STUDENT_MAIN_FRAME_MY_COURSE_IMAGE_PATH, myCourseButton);
		setIcon(StudentFrameConstant.STUDENT_MAIN_FRAME_SELECT_COURSE_IMAGE_PATH, selectCourseButton);
	}
	
	private void initPanel() {
		centrePanel = new JPanel();
		centrePanel.setLayout(null);
		centrePanel.setLocation(100, 80);
		centrePanel.setSize(400, 400);
		centrePanel.add(myManageButton);
		centrePanel.add(myClassButton);
		centrePanel.add(myCourseButton);
		centrePanel.add(selectCourseButton);
		centrePanel.add(exitButton);
	}

	private void init() {
		this.initButton();
		this.initPanel();
		this.initService();
		this.setTitle("学生主界面~");
		this.setLocation(50, 50);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.add(centrePanel);	
	} 
	
	private StudentMainFrame getStudentMainFrame() {
		return this;
	}
	
	Map<Integer, Teacher> getTeacherMap() {
		return teacherMap;
	}

	Map<Integer, Course> getCourseMap() {
		return courseMap;
	}

	List<SelectCourse> getSelectCourseList() {
		return selectCourseList;
	}

	SelectCourseService getSelectCourseService() {
		return selectCourseService;
	}

	void setSelectCourseList(List<SelectCourse> selectCourseList) {
		this.selectCourseList = selectCourseList;
	}
	
	private void setIcon(String file, JButton iconButton) {
        ImageIcon icon = new ImageIcon(file);  
        Image temp = icon.getImage().getScaledInstance(iconButton.getWidth()/4,  
                iconButton.getHeight()/4, Image.SCALE_DEFAULT);
        icon = new ImageIcon(temp);  
        iconButton.setIcon(icon);  
    } 
}
