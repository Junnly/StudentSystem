package com.pzh.www.view.teacher;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.pzh.www.constant.TeacherFrameConstant;
import com.pzh.www.po.Student;
import com.pzh.www.view.CheckDialog;

/**
 * 查找学生的窗口
 * @author Pan梓涵
 */
public class FindStudentDialog extends JDialog {
	
	private JLabel studentNoLabel;					//学生 编号 标签
	private JLabel studentNameLabel;				//学生 name 标签
	private JTextField studentNoTextField;		//学生 编号 文本框
	private JTextField studentNameTextField;	//学生 name 文本框
	private JButton findByNoButton;					//根据 编号 查询的查询按钮
	private JButton findByNameButton;			//根据 name 查询的查询按钮
	private TeacherMainFrame teacherMainFrame;	//保留对原窗体的引用

	/**
	 * 初始化 标签
	 */
	private void initLabel() {
		studentNoLabel = new JLabel("根据studentNo查询   No：",JLabel.CENTER);
		studentNoLabel.setFont(TeacherFrameConstant.FIND_STUDENT_DIALOG_LABEL_FONT);
		studentNameLabel = new JLabel("根据name的模糊查询   name：",JLabel.CENTER);
		studentNameLabel.setFont(TeacherFrameConstant.FIND_STUDENT_DIALOG_LABEL_FONT);
	}
	
	/**
	 * 初始化 文本框
	 */
	private void initTextField() {
		studentNoTextField = new JTextField(15);
		studentNameTextField = new JTextField(15);
	}
	
	/**
	 * 初始化 按钮
	 */
	private void initButton() {
		initFindByNoButton();
		initFindByNameButton();
	}

	/**
	 * 设置 查询(No) 确定按钮
	 */
	private void initFindByNoButton() {
		findByNoButton = new JButton(TeacherFrameConstant.FIND_STUDENT_DIALOG_FIND_BYNO_BUTTON);
		findByNoButton.setFont(TeacherFrameConstant.FIND_STUDENT_DIALOG_BUTTON_FONT);
		findByNoButton.setBackground(new Color(1,158,151));
		findByNoButton.setForeground(Color.WHITE);
		findByNoButton.addActionListener(new ButtonActionListener());
	}
	
	/**
	 * 设置 查询(name) 确定按钮
	 */
	private void initFindByNameButton() {
		findByNameButton = new JButton(TeacherFrameConstant.FIND_STUDENT_DIALOG_FIND_BYNAME_BUTTON);
		findByNameButton.setFont(TeacherFrameConstant.FIND_STUDENT_DIALOG_BUTTON_FONT);
		findByNameButton.setBackground(new Color(1,158,151));
		findByNameButton.setForeground(Color.WHITE);
		findByNameButton.addActionListener(new ButtonActionListener());
	}
	
	/**
	 * 无参数构造器
	 */
	public FindStudentDialog() {
		
	}
	
	/**
	 * 含参数构造器
	 */
	FindStudentDialog(JFrame frame) {
		super(frame);
		if(frame instanceof TeacherMainFrame) {
			teacherMainFrame = (TeacherMainFrame) frame;
		}
		initialize();
		setVisible(true);
		setResizable(false);
	}
	
	/**
	 * 初始化 窗体
	 */
	private void initialize() {
		this.initLabel();			//初始化标签
		this.initTextField(); 	//初始化文本框
		this.initButton(); 		//初始化按钮
		this.setTitle("查找学生");
		this.setSize(227, 237);
		this.setLocation(350, 100);
		this.setLayout(new FlowLayout());
		this.add(studentNoLabel);
		this.add(studentNoTextField);
		this.add(findByNoButton);
		this.add(studentNameLabel);
		this.add(studentNameTextField);
		this.add(findByNameButton);
	}
	
	/**
	 * 返回一个本窗体的引用
	 */
	private FindStudentDialog getFindStudentDialog() {
		return this;
	}
	
	/**
	 * 按钮的监听事件
	 */
	class ButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//查找(No)按钮的监听事件
			List<Student> list;
			if(e.getActionCommand().equals(TeacherFrameConstant.FIND_STUDENT_DIALOG_FIND_BYNO_BUTTON)) {
				if(studentNoTextField.getText().trim().length() > 0) {
					list = teacherMainFrame.getStudentService().findStudentByNo(studentNoTextField.getText());
					buttonAction(list);
				}else {
					new CheckDialog(getFindStudentDialog(),"查找No不能为空").setVisible(true);
				}
			}
			
			//查找(name)按钮的监听事件
			if(e.getActionCommand().equals(TeacherFrameConstant.FIND_STUDENT_DIALOG_FIND_BYNAME_BUTTON)) {
				if(studentNameTextField.getText().trim().length() > 0) {
					list = teacherMainFrame.getStudentService().findStudentByName(studentNameTextField.getText());
					buttonAction(list);
				}else {
					new CheckDialog(getFindStudentDialog(),"查找Name不能为空").setVisible(true);
				}
			}
			
		}

		private void buttonAction(List<Student> list) {
			if(list.size() > 0) {
				getFindStudentDialog().setVisible(false);
				getFindStudentDialog().dispose();
				teacherMainFrame.setListShow(list);
			}else {
				new CheckDialog(getFindStudentDialog(),"sorry,数据库无此资料！").setVisible(true);
			}
		}

	}
}
