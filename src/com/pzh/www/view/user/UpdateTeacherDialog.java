package com.pzh.www.view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pzh.www.view.CheckDialog;
import com.pzh.www.constant.UserFrameConstant;
import com.pzh.www.po.Teacher;

/**
 * 更新老师窗口
 * @author Pan梓涵
 */
public class UpdateTeacherDialog extends JDialog {
	private JLabel teacherNameLabel;				//老师 name 标签
	private JLabel teacherGradeLabel;				//老师 管理年级 标签
	private JLabel teacherClassLabel;					//老师 管理班级 标签
	private JLabel teacherPhoneLabel;				//老师 联系电话 标签
	private JLabel teacherEmailLabel;				//老师 邮箱 标签
	private JLabel teacherNoLabel;					//教工 编号 标签
	private JLabel teacherNoLabel2;					//显示 教工具体编号
	private JTextField teacherNameTextField;	//老师 name 文本框
	private JTextField teacherGradeTextField;	//老师 管理年级 文本框
	private JTextField teacherClassTextField;		//老师 管理班级 文本框
	private JTextField teacherPhoneTextField;	//老师 联系电话 文本框
	private JTextField teacherEmailTextField;		//老师 邮箱 文本框
	private JButton deleteButton;						//删除按钮
	private JButton updateButton;					//更新按钮
	private Teacher teacher = null;					//用于存放老师对象
	private String teacherNo;
	private UserMainFrame userMainFrame;
	
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
		teacherNoLabel2 = new JLabel("",JLabel.CENTER);
		
		teacherNameLabel.setFont(UserFrameConstant.UPDATE_TEACHER_DIALOG_LABEL_FONT);
		teacherGradeLabel.setFont(UserFrameConstant.UPDATE_TEACHER_DIALOG_LABEL_FONT);
		teacherClassLabel.setFont(UserFrameConstant.UPDATE_TEACHER_DIALOG_LABEL_FONT);
		teacherPhoneLabel.setFont(UserFrameConstant.UPDATE_TEACHER_DIALOG_LABEL_FONT);
		teacherEmailLabel.setFont(UserFrameConstant.UPDATE_TEACHER_DIALOG_LABEL_FONT);
		teacherNoLabel.setFont(UserFrameConstant.UPDATE_TEACHER_DIALOG_LABEL_FONT);
		teacherNoLabel2.setFont(UserFrameConstant.UPDATE_TEACHER_DIALOG_LABEL_FONT);
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
	}
	
	/*
	 * 初始化 update 和 delete 按钮
	 */
	private void initializeButton() {
		updateButton = new JButton(UserFrameConstant.UPDATE_TEACHER_DIALOG_UPDATE_BUTTON);
		updateButton.setFont(UserFrameConstant.UPDATE_TEACHER_DIALOG_BUTTON_FONT);
		updateButton.setBackground(new Color(1,158,151));
		updateButton.setForeground(Color.WHITE);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				teacher = new Teacher();
				teacher.setTeacherNo(teacherNo);
				teacher.setTeacherName(teacherNameTextField.getText());
				teacher.setTeacherGrade(teacherGradeTextField.getText());
				teacher.setTeacherClass(teacherClassTextField.getText());
				teacher.setTeacherPhone(teacherPhoneTextField.getText());
				teacher.setTeacherEmail(teacherEmailTextField.getText());
				if( userMainFrame.getTeacherService().updateTeacher(teacher)) {
					new CheckDialog(getUpdateTeacherDialog(), "保存成功!").setVisible(true);
				}else {
					new CheckDialog(getUpdateTeacherDialog(), "保存失败!").setVisible(true);
				}
			}
		});
		
		deleteButton = new JButton(UserFrameConstant.UPDATE_TEACHER_DIALOG_DELETE_BUTTON);
		deleteButton.setFont(UserFrameConstant.UPDATE_TEACHER_DIALOG_BUTTON_FONT);
		deleteButton.setBackground(new Color(1,158,151));
		deleteButton.setForeground(Color.WHITE);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userMainFrame.getTeacherService().deleteTeacher(teacherNo)) {
					getUpdateTeacherDialog().setVisible(false);
					new CheckDialog(getUpdateTeacherDialog(), "删除成功!").setVisible(true);
					getUpdateTeacherDialog().dispose();
				}else {
					new CheckDialog(getUpdateTeacherDialog(), "删除失败!").setVisible(true);
				}
			}
		});
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
	
	public UpdateTeacherDialog getUpdateTeacherDialog() {
		return this;
	}
	
	public JLabel getTeacherNoLabel2() {
		return teacherNoLabel2;
	}

	public UpdateTeacherDialog(){
		
	}
	
	/*
	 * 含参数的构造器
	 */
	public UpdateTeacherDialog(UserMainFrame frame,String teachNo){
		super(frame,"teacher data",true);
		teacherNo = teachNo;
		this.userMainFrame = frame;
		JPanel c = new JPanel();
		initializeLabel();							//初始化 各种标签
		initializeTextField();					//初始化 各种文本框
		initializeButton();						//初始化 按钮
		c.add(teacherNoLabel);				//教工 编号 标签
		c.add(teacherNoLabel2);			//教工 编号 具体内容
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
		this.setSize(198, 350);
		this.setLocation(350, 100);
		this.setLayout(new BorderLayout());
		this.add(c,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
		this.add(buttonPanel,BorderLayout.SOUTH);
		this.setResizable(false);
	}
}
