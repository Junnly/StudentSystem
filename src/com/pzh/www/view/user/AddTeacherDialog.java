package com.pzh.www.view.user;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pzh.www.view.CheckDialog;
import com.pzh.www.constant.UserFrameConstant;
import com.pzh.www.po.Teacher;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * 添加老师窗口
 * @author Pan梓涵
 */
public class AddTeacherDialog extends JDialog{
	
	private JLabel teacherNameLabel;				//老师 name 标签
	private JLabel teacherGradeLabel;				//老师 管理年级 标签
	private JLabel teacherClassLabel;					//老师 管理班级 标签
	private JLabel teacherPhoneLabel;				//老师 联系电话 标签
	private JLabel teacherEmailLabel;				//老师 邮箱 标签
	private JLabel teacherNoLabel;					//老师 编号 标签
	private JLabel teacherPasswordLabel;			//老师 密码 标签
	private JTextField teacherNameTextField;	//老师 name 文本框
	private JTextField teacherGradeTextField;	//老师 管理年级 文本框
	private JTextField teacherClassTextField;		//老师 管理班级 文本框
	private JTextField teacherPhoneTextField;	//老师 联系电话 文本框
	private JTextField teacherEmailTextField;		//老师 邮箱 文本框
	private JTextField teacherNoTextField;		//老师 编号 文本框
	private JTextField teacherPasswordTextField;//老师 密码 文本框
	private JButton okButton;							//确定 按钮
	private JButton cancelButton;						//取消 按钮
	private JButton continueAddButton;			//继续 添加按钮
	private JButton backButton;                		//返回 按钮
	private Teacher teacher ;								//老师 对象
	private CheckDialog checkDialog;				//检查 窗体
	private UserMainFrame userMainFrame;		//保留主窗体的引用
	
	/*
	 * 初始化 检查 窗体
	 */
	public CheckDialog getCheckDialog() {
		checkDialog = new CheckDialog(getAddTeacherDialog());
		return checkDialog;
	}

	/*
	 * 初始化 继续添加 按钮
	 */
	public JButton getContinueAddButton() {
		continueAddButton = new JButton(UserFrameConstant.ADD_TEACHER_DIALOG_CONTINUEADD_BUTTON);
		continueAddButton.setFont(UserFrameConstant.ADD_TEACHER_DIALOG_BUTTON_FONT);
		continueAddButton.setBackground(new Color(1,158,151));
		continueAddButton.setForeground(Color.WHITE);
		continueAddButton.addActionListener(new ActionListener() {
			//继续添加按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				checkDialog.setVisible(false);
				checkDialog.dispose();
				teacherNameTextField.setText("");	
				teacherGradeTextField.setText("");	
				teacherClassTextField.setText("");	
				teacherPhoneTextField.setText("");	
				teacherEmailTextField.setText("");
				teacherNoTextField.setText("");
				teacherPasswordTextField.setText("");
			}
		});
		return continueAddButton;
	}

	/*
	 * 初始化 返回 按钮
	 */
	public JButton getBackButton() {
		backButton = new JButton(UserFrameConstant.ADD_TEACHER_DIALOG_BACK_BUTTON);
		backButton.setFont(UserFrameConstant.ADD_TEACHER_DIALOG_BUTTON_FONT);
		backButton.setBackground(new Color(1,158,151));
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(new ActionListener() {
			//返回 按钮的监听事件
			@Override
			public void actionPerformed(ActionEvent e) {
				checkDialog.setVisible(false);
				checkDialog.dispose();
				getAddTeacherDialog().setVisible(false);
				getAddTeacherDialog().dispose();
			}
		});
		return backButton;
	}
	
	/*
	 * 初始化标签
	 */
	private void initializeLabel() {
		teacherNameLabel = new JLabel("老师名字：",JLabel.CENTER);
		teacherGradeLabel = new JLabel("管理年级：",JLabel.CENTER);
		teacherClassLabel = new JLabel("管理班级：",JLabel.CENTER);
		teacherPhoneLabel = new JLabel("联系电话：",JLabel.CENTER);
		teacherEmailLabel = new JLabel("邮箱：",JLabel.CENTER);
		teacherNoLabel = new JLabel("教工编号：",JLabel.CENTER);
		teacherPasswordLabel = new JLabel("密码：",JLabel.CENTER);
		
		teacherNameLabel.setFont(UserFrameConstant.ADD_TEACHER_DIALOG_LABEL_FONT);
		teacherGradeLabel.setFont(UserFrameConstant.ADD_TEACHER_DIALOG_LABEL_FONT);
		teacherClassLabel.setFont(UserFrameConstant.ADD_TEACHER_DIALOG_LABEL_FONT);
		teacherPhoneLabel.setFont(UserFrameConstant.ADD_TEACHER_DIALOG_LABEL_FONT);
		teacherEmailLabel.setFont(UserFrameConstant.ADD_TEACHER_DIALOG_LABEL_FONT);
		teacherNoLabel.setFont(UserFrameConstant.ADD_TEACHER_DIALOG_LABEL_FONT);
		teacherPasswordLabel.setFont(UserFrameConstant.ADD_TEACHER_DIALOG_LABEL_FONT);
	}
	
	/*
	 * 初始化文本框
	 */
	private void initializeTextField() {
		teacherNameTextField = new JTextField(15);
		teacherGradeTextField = new JTextField(15);
		teacherClassTextField = new JTextField(15);
		teacherPhoneTextField = new JTextField(15);
		teacherEmailTextField = new JTextField(15);
		teacherNoTextField = new JTextField(15);
		teacherPasswordTextField = new JTextField(15);
	}
	
	public JTextField getTeacherNameTextField() {
		return teacherNameTextField;
	}

	public JTextField getTeacherGradeTextField() {
		return teacherGradeTextField;
	}

	public JTextField getTeacherClassTextField() {
		return teacherClassTextField;
	}

	public JTextField getTeacherPhoneTextField() {
		return teacherPhoneTextField;
	}

	public JTextField getTeacherEmailTextField() {
		return teacherEmailTextField;
	}
	
	/*
	 * 初始化 确定 按钮
	 */
	public JButton getOkButton(){
		okButton = new JButton(UserFrameConstant.ADD_TEACHER_DIALOG_OK_BUTTON);
		okButton.setFont(UserFrameConstant.ADD_TEACHER_DIALOG_BUTTON_FONT);
		okButton.setBackground(new Color(1,158,151));
		okButton.setForeground(Color.WHITE);
		okButton.addActionListener(new ActionListener() {
			//确定按钮的监听事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkAddTeacherTable()) {
					teacher = new Teacher();
					teacher.setTeacherName(teacherNameTextField.getText());
					teacher.setTeacherGrade(teacherGradeTextField.getText());
					teacher.setTeacherClass(teacherClassTextField.getText());
					teacher.setTeacherPhone(teacherPhoneTextField.getText());
					teacher.setTeacherEmail(teacherEmailTextField.getText());
					teacher.setTeacherNo(teacherNoTextField.getText());
					teacher.setTeacherPassword(teacherPasswordTextField.getText());
					
					if(userMainFrame.getTeacherService().addTeacher(teacher)) {
						checkDialog = getCheckDialog();
						checkDialog.setLayout(new FlowLayout());
						JLabel jlabel = new JLabel("添加成功!",JLabel.CENTER);	//创建一个提示的标签
						jlabel.setFont(new Font("微软雅黑",Font.BOLD,18));			 //设置标签的字体样式
						checkDialog.add(jlabel);
						checkDialog.add(continueAddButton);
						checkDialog.add(backButton);
						checkDialog.setSize(180, 120);
						checkDialog.setVisible(true);
					}else {
						checkDialog = new CheckDialog(getAddTeacherDialog(), "教工编号已存在!");
						checkDialog.setVisible(true);
					}
					
				}
			}
		});
		return okButton;
	}
	
	/*
	 * 初始化 取消 按钮
	 */
	public JButton getCancelButton(){
		cancelButton = new JButton(UserFrameConstant.ADD_TEACHER_DIALOG_CANCEL_BUTTON);
		cancelButton.setFont(UserFrameConstant.ADD_TEACHER_DIALOG_BUTTON_FONT);
		cancelButton.setBackground(new Color(1,158,151));
		cancelButton.setForeground(Color.WHITE);
		cancelButton.addActionListener(new ActionListener() {
			//取消按钮的监听事件
			@Override
			public void actionPerformed(ActionEvent e) {
				teacherNameTextField.setText("");	
				teacherGradeTextField.setText("");	
				teacherClassTextField.setText("");	
				teacherPhoneTextField.setText("");	
				teacherEmailTextField.setText("");
				teacherNoTextField.setText("");
				teacherPasswordTextField.setText("");
				getAddTeacherDialog().setVisible(false);
			}
		});
		return cancelButton;
	}
	
	/*
	 * 返回 本类 引用
	 */
	public AddTeacherDialog getAddTeacherDialog() {
		return this;
	}

	/*
	 * 不带参数的构造器
	 */
	public AddTeacherDialog() {
		
	}
	
	/*
	 * 含参数的构造器
	 */
	public AddTeacherDialog(UserMainFrame frame) {
		super(frame,"添加老师",true);
		this.userMainFrame = frame;
		JPanel c = new JPanel();
		initializeLabel();							//初始化 各种标签
		initializeTextField();					//初始化 各种文本框
		c.add(teacherNameLabel);			//老师 name
		c.add(teacherNameTextField);
		c.add(teacherGradeLabel);			//老师 管理年级
		c.add(teacherGradeTextField);	
		c.add(teacherClassLabel);			//老师 管理班级
		c.add(teacherClassTextField);	
		c.add(teacherPhoneLabel);		//老师 联系电话
		c.add(teacherPhoneTextField);
		c.add(teacherEmailLabel);			//老师 邮箱
		c.add(teacherEmailTextField);
		c.add(teacherNoLabel);				//教工 编号
		c.add(teacherNoTextField);
		c.add(teacherPasswordLabel);	//密码
		c.add(teacherPasswordTextField);
		c.add(getOkButton());				//确定 按钮
		c.add(getCancelButton());			//取消 按钮
		continueAddButton = getContinueAddButton();		//初始化继续添加按钮
		backButton = getBackButton();									//初始化返回按钮
		this.add(c);
		this.setSize(220, 420);
		this.setLocation(350, 100);
		this.setResizable(false);
	}
	
	/*
	 * 添加表的表单验证
	 */
	private boolean checkAddTeacherTable() {
		if("".equals(teacherNameTextField.getText().trim())) {
			new CheckDialog(this, "请填写老师名字！").setVisible(true);
			return false;
		}
		if("".equals(teacherGradeTextField.getText().trim())) {
			new CheckDialog(this, "请填写管理年级！").setVisible(true);
			return false;
		}
		if("".equals(teacherClassTextField.getText().trim())) {
			new CheckDialog(this, "请填写管理班级！").setVisible(true);
			return false;
		}
		if("".equals(teacherPhoneTextField.getText().trim())) {
			new CheckDialog(this, "请填写联系电话！").setVisible(true);
			return false;
		}
		if("".equals(teacherEmailTextField.getText().trim())) {
			new CheckDialog(this, "请填写邮箱！").setVisible(true);
			return false;
		}
		if("".equals(teacherNoTextField.getText().trim())) {
			new CheckDialog(this, "请填写教工编号！").setVisible(true);
			return false;
		}
		if("".equals(teacherPasswordTextField.getText().trim())) {
			new CheckDialog(this, "请填写密码！").setVisible(true);
			return false;
		}
		return true;
	}
}
