package com.pzh.www.view.student;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.pzh.www.constant.StudentFrameConstant;
import com.pzh.www.po.Student;
import com.pzh.www.service.MyClassService;

/**
 * 我的信息窗口
 * @author Pan梓涵
 */
class MyManageFrame extends JFrame{

	private static final long serialVersionUID = -4029835765699377191L;
	private JLabel titleLabel;//标题标签
	private JLabel studentNoLabel;//学号标签
	private JLabel studentNameLabel;//姓名标签
	private JLabel studentGenderLabel;//性别标签
	private JLabel studentPhoneLabel;//紧急联系电话标签
	private JLabel studentEmailLabel;//邮箱标签
	private JLabel classLabel;//班级标签
	private MyClassService myClassService;//班级对象
	
	/**
	 * 初始化标签
	 */
	private void initLabel() {
		titleLabel = new JLabel("个人资料",JLabel.CENTER);
		studentNameLabel = new JLabel("",JLabel.CENTER);
		studentGenderLabel = new JLabel("",JLabel.CENTER);
		studentPhoneLabel = new JLabel("",JLabel.CENTER);
		studentEmailLabel = new JLabel("",JLabel.CENTER);
		studentNoLabel = new JLabel("",JLabel.CENTER);
		classLabel = new JLabel("",JLabel.CENTER);
		
		titleLabel.setFont(new Font("微软雅黑",Font.BOLD,16));
		studentNameLabel.setFont(StudentFrameConstant.MY_MANAGE_FRAME_LABEL_FONT);
		studentGenderLabel.setFont(StudentFrameConstant.MY_MANAGE_FRAME_LABEL_FONT);
		studentPhoneLabel.setFont(StudentFrameConstant.MY_MANAGE_FRAME_LABEL_FONT);
		studentEmailLabel.setFont(StudentFrameConstant.MY_MANAGE_FRAME_LABEL_FONT);
		studentNoLabel.setFont(StudentFrameConstant.MY_MANAGE_FRAME_LABEL_FONT);
		classLabel.setFont(StudentFrameConstant.MY_MANAGE_FRAME_LABEL_FONT);
	}
	
	/**
	 * 初始化窗体
	 */
	private void init() {
		initLabel();
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(7, 1));
		jp.add(titleLabel);
		jp.add(studentNoLabel);
		jp.add(studentNameLabel);
		jp.add(studentGenderLabel);
		jp.add(classLabel);
		jp.add(studentPhoneLabel);
		jp.add(studentEmailLabel);
		this.add(jp);
		this.setTitle("个人信息界面");
		this.setLocation(100, 100);
		this.setSize(300, 350);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}
	
	/**
	 * 构造方法
	 */
	MyManageFrame(Student s) {
		init();
		this.myClassService = MyClassService.getInstance();
		this.setVisible(true);
		this.showStudent(s);
	}
	
	/**
	 * 展示学生的资料
	 */
	private void showStudent(Student s) {
		studentNoLabel.setText("学号：" + s.getStudentNo());
		studentNameLabel.setText("姓名：" + s.getStudentName());
		studentGenderLabel.setText("性别：" + s.getStudentGender());
		classLabel.setText("班级：" + myClassService.getClassNameByClassNo(s.getClassId()));
		studentPhoneLabel.setText("紧急联系电话：" + s.getStudentPhone());
		studentEmailLabel.setText("邮箱：" + s.getStudentEmail());
	}
}
