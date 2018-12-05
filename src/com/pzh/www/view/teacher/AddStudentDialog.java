package com.pzh.www.view.teacher;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pzh.www.constant.TeacherFrameConstant;
import com.pzh.www.po.MyClass;
import com.pzh.www.po.Student;
import com.pzh.www.view.CheckDialog;

/**
 * 添加学生窗口
 * @author Pan梓涵
 */
public class AddStudentDialog extends JDialog{
	
	private JLabel studentNameLabel;				//学生 name 标签
	private JLabel studentGenderLabel;				//学生 性别  标签
	private JLabel studentPhoneLabel;				//学生 紧急联系电话 标签
	private JLabel studentEmailLabel;				//学生 邮箱 标签
	private JLabel studentNoLabel;					//学生 学号 标签
	private JLabel studentPasswordLabel;			//学生 密码 标签
	private JLabel studentClassLabel;					//班级
	private JTextField studentNameTextField;	//学生 name 文本框
	private JTextField studentGenderTextField;	//学生 性别 文本框
	private JTextField studentPhoneTextField;	//学生 紧急联系电话 文本框
	private JTextField studentEmailTextField;	//学生 邮箱 文本框
	private JTextField studentNoTextField;		//学生 学号 文本框
	private JTextField studentPasswordTextField;//学生 密码 文本框
	private JButton okButton;							//确定 按钮
	private JButton cancelButton;						//取消 按钮
	private JButton continueAddButton;			//继续 添加按钮
	private JButton backButton;                 		//返回 按钮
	private JComboBox<String> jc;
	private Student student;								//学生 对象
	private CheckDialog checkDialog;				//检查 窗体
	private TeacherMainFrame teacherMainFrame;
	
	/*
	 * 确定按钮的设置
	 */
	public void setOkButton() {
		okButton = new JButton(TeacherFrameConstant.ADD_STUDENT_DIALOG_OK_BUTTON);
		okButton.setFont(TeacherFrameConstant.ADD_STUDENT_DIALOG_BUTTON_FONT);
		okButton.setBackground(new Color(1,158,151));
		okButton.setForeground(Color.WHITE);
		okButton.addActionListener(new ButtonActionListener());
	}

	/*
	 * 取消按钮的设置
	 */
	public void setCancelButton() {
		cancelButton = new JButton(TeacherFrameConstant.ADD_STUDENT_DIALOG_CANCEL_BUTTON);
		cancelButton.setFont(TeacherFrameConstant.ADD_STUDENT_DIALOG_BUTTON_FONT);
		cancelButton.setBackground(new Color(1,158,151));
		cancelButton.setForeground(Color.WHITE);
		cancelButton.addActionListener(new ButtonActionListener());
	}

	/*
	 * 继续添加按钮的设置
	 */
	public void setContinueAddButton() {
		continueAddButton = new JButton(TeacherFrameConstant.ADD_STUDENT_DIALOG_CONTINUE_ADD_BUTTON);
		continueAddButton.setFont(TeacherFrameConstant.ADD_STUDENT_DIALOG_BUTTON_FONT);
		continueAddButton.setBackground(new Color(1,158,151));
		continueAddButton.setForeground(Color.WHITE);		
		continueAddButton.addActionListener(new ButtonActionListener());
	}

	/*
	 * 返回按钮的设置
	 */
	public void setBackButton() {
		backButton = new JButton(TeacherFrameConstant.ADD_STUDENT_DIALOG_BACK_BUTTON);
		backButton.setFont(TeacherFrameConstant.ADD_STUDENT_DIALOG_BUTTON_FONT);
		backButton.setBackground(new Color(1,158,151));
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(new ButtonActionListener());
	}

	/*
	 * 初始化 检查 窗体
	 */
	public CheckDialog getCheckDialog() {
		checkDialog = new CheckDialog(getAddStudentDialog());
		return checkDialog;
	}
	
	/*
	 * 初始化标签
	 */
	public void initializeLabel() {
		studentNameLabel = new JLabel("学生名字：",JLabel.CENTER);
		studentGenderLabel = new JLabel("性别：",JLabel.CENTER);
		studentPhoneLabel = new JLabel("紧急联系电话：",JLabel.CENTER);
		studentEmailLabel = new JLabel("邮箱：",JLabel.CENTER);
		studentNoLabel = new JLabel("学号：",JLabel.CENTER);
		studentPasswordLabel = new JLabel("密码：",JLabel.CENTER);
		studentClassLabel = new JLabel("班级:",JLabel.CENTER);
		
		studentNameLabel.setFont(TeacherFrameConstant.ADD_STUDENT_DIALOG_LABEL_FONT);
		studentGenderLabel.setFont(TeacherFrameConstant.ADD_STUDENT_DIALOG_LABEL_FONT);
		studentPhoneLabel.setFont(TeacherFrameConstant.ADD_STUDENT_DIALOG_LABEL_FONT);
		studentEmailLabel.setFont(TeacherFrameConstant.ADD_STUDENT_DIALOG_LABEL_FONT);
		studentNoLabel.setFont(TeacherFrameConstant.ADD_STUDENT_DIALOG_LABEL_FONT);
		studentPasswordLabel.setFont(TeacherFrameConstant.ADD_STUDENT_DIALOG_LABEL_FONT);
		studentClassLabel.setFont(TeacherFrameConstant.ADD_STUDENT_DIALOG_LABEL_FONT);
	}
	
	/*
	 * 初始化文本框
	 */
	public void initializeTextField() {
		studentNameTextField = new JTextField(15);
		studentGenderTextField = new JTextField(15);
		studentPhoneTextField = new JTextField(15);
		studentEmailTextField = new JTextField(15);
		studentNoTextField = new JTextField(15);
		studentPasswordTextField = new JTextField(15);
	}
	
	/*
	 * 初始化按钮
	 */
	public void initializeButton() {
		setOkButton();			//确定 按钮
		setCancelButton();		//取消 按钮
		setContinueAddButton();	//继续 添加按钮
		setBackButton(); 		//返回 按钮
	}
	
	/*
	 * 返回一个本类对象的引用
	 */
	public AddStudentDialog getAddStudentDialog() {
		return this;
	}
	
	/*
	 * 不带参数的构造器
	 */
	public AddStudentDialog() {
		
	}
	
	/*
	 * 含参数的构造器
	 */
	public AddStudentDialog(TeacherMainFrame frame) {
		super(frame,"添加学生资料",true);
		this.teacherMainFrame = frame;
		JPanel c = new JPanel();
		initializeLabel();						//初始化 各种标签
		initializeTextField();				//初始化 各种文本框
		initializeButton();					//初始化 各种按钮
		jc = new JComboBox<>(new MyComboBox(teacherMainFrame.getMyClassList()));
		jc.setFont(getFont());
		c.add(studentNameLabel);
		c.add(studentNameTextField);
		c.add(studentGenderLabel);
		c.add(studentGenderTextField);
		c.add(studentClassLabel);
		c.add(jc);
		c.add(studentPhoneLabel);
		c.add(studentPhoneTextField);
		c.add(studentEmailLabel);
		c.add(studentEmailTextField);
		c.add(studentNoLabel);
		c.add(studentNoTextField);
		c.add(studentPasswordLabel);
		c.add(studentPasswordTextField);
		c.add(okButton);					//确定 按钮
		c.add(cancelButton);				//取消 按钮
		c.setLayout(new GridLayout(8, 2));
		this.add(c);
		this.setSize(300, 250);
		this.setLocation(350, 100);
		this.setResizable(false);
	}
	
	/*
	 * 添加表的表单验证
	 */
	private boolean checkAddStudentTable() {
		if("".equals(studentNameTextField.getText().trim())) {
			new CheckDialog(this, "请填写学生名字！").setVisible(true);
			return false;
		}
		if("".equals(studentGenderTextField.getText().trim())) {
			new CheckDialog(this, "请填写性别！").setVisible(true);
			return false;
		}
		if(jc.getSelectedItem() == null) {
			new CheckDialog(this, "请选择班级！").setVisible(true);
			return false;
		}
		if("".equals(studentPhoneTextField.getText().trim())) {
			new CheckDialog(this, "请填写紧急联系电话！").setVisible(true);
			return false;
		}
		if("".equals(studentEmailTextField.getText().trim())) {
			new CheckDialog(this, "请填写邮箱！").setVisible(true);
			return false;
		}
		if("".equals(studentNoTextField.getText().trim())) {
			new CheckDialog(this, "请填写学生学号！").setVisible(true);
			return false;
		}
		if("".equals(studentPasswordTextField.getText().trim())) {
			new CheckDialog(this, "请填写学生密码！").setVisible(true);
			return false;
		}
		return true;
	}
	
	/*
	 * 按钮的监听事件
	 */
	class ButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//取消按钮的监听事件
			if(e.getActionCommand().equals(TeacherFrameConstant.ADD_STUDENT_DIALOG_CANCEL_BUTTON)) {
				getAddStudentDialog().setVisible(false);
				getAddStudentDialog().dispose();
			}
			
			//确认按钮的监听事件
			if(e.getActionCommand().equals(TeacherFrameConstant.ADD_STUDENT_DIALOG_OK_BUTTON)) {
				if(checkAddStudentTable()) {
					student = new Student();
					student.setStudentName(studentNameTextField.getText());
					student.setStudentGender(studentGenderTextField.getText());
					student.setStudentPhone(studentPhoneTextField.getText());
					student.setStudentEmail(studentEmailTextField.getText());
					student.setStudentNo(studentNoTextField.getText());
					student.setStudentPassword(studentPasswordTextField.getText());
					student.setClassId(getMyClassIdFromMyClassList(jc.getSelectedItem() + ""));
					
					if(teacherMainFrame.getStudentService().addStudent(student)) {
						checkDialog = getCheckDialog();
						checkDialog.setLayout(new FlowLayout());
						JLabel jlabel = new JLabel("添加成功!",JLabel.CENTER);//创建一个提示的标签
						jlabel.setFont(new Font("微软雅黑",Font.BOLD,18)); 		//设置标签的字体样式
						checkDialog.add(jlabel);
						checkDialog.add(continueAddButton);
						checkDialog.add(backButton);
						checkDialog.setSize(180, 120);
						checkDialog.setVisible(true);
					}else {
						checkDialog = new CheckDialog(getAddStudentDialog(), "该学号已存在!");
						checkDialog.setVisible(true);
					}
				}
			}
			
			//继续添加按钮的监听事件
			if(e.getActionCommand().equals(TeacherFrameConstant.ADD_STUDENT_DIALOG_CONTINUE_ADD_BUTTON)) {
				checkDialog.setVisible(false);
				checkDialog.dispose();
				studentNameTextField.setText("");
				studentGenderTextField.setText("");
				studentPhoneTextField.setText("");
				studentEmailTextField.setText("");
				studentNoTextField.setText("");
				studentPasswordTextField.setText("");
			}
			
			//返回按钮的监听事件
			if(e.getActionCommand().equals(TeacherFrameConstant.ADD_STUDENT_DIALOG_BACK_BUTTON)) {
				checkDialog.setVisible(false);
				checkDialog.dispose();
				getAddStudentDialog().setVisible(false);
				getAddStudentDialog().dispose();
			}
		}	
		
	}
	
	private int getMyClassIdFromMyClassList(String className) {
		for (MyClass myClass : teacherMainFrame.getMyClassList()) {
			if(className.equals(myClass.getClassName())) {
				return myClass.getClassNo();
			}
		}
		return -1;
	}
	
}
