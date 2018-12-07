package com.pzh.www.view.teacher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pzh.www.constant.TeacherFrameConstant;
import com.pzh.www.po.Student;
import com.pzh.www.service.StudentService;
import com.pzh.www.view.CheckDialog;

/**
 * 更新学生窗口
 * @author Pan梓涵
 */
public class UpdateStudentDialog extends JDialog {
	
	private JLabel studentNameLabel;			//学生 name 标签
	private JLabel studentGenderLabel;			//学生 性别 标签
	private JLabel studentPhoneLabel;			//学生 紧急联系电话 标签
	private JLabel studentEmailLabel;			//学生 邮箱 标签
	private JLabel studentNoLabel;				//学生 学号 标签
	private JLabel studentNoLabel2;				//显示 具体学号
	private JLabel studentClassLabel;				//班级
	private JComboBox<String> jc;				//班级列表框
	private JTextField studentNameTextField;	//学生 name 文本框
	private JTextField studentGenderTextField;	//学生 性别 文本框
	private JTextField studentPhoneTextField;	//学生 紧急联系电话 文本框
	private JTextField studentEmailTextField;	//学生 邮箱 文本框
	private JButton deleteButton;				//删除按钮
	private JButton updateButton;				//更新按钮
	private Student student;					//用于存放老师对象
	private String studentNo;
	
	/**
	 * 初始化标签
	 */
	private void initLabel() {
		studentNameLabel = new JLabel("学生名字：",JLabel.CENTER);
		studentGenderLabel = new JLabel("性别：",JLabel.CENTER);
		studentPhoneLabel = new JLabel("紧急联系电话：",JLabel.CENTER);
		studentEmailLabel = new JLabel("邮箱：",JLabel.CENTER);
		studentNoLabel = new JLabel("学号：",JLabel.CENTER);
		studentNoLabel2 = new JLabel("",JLabel.CENTER);
		studentClassLabel = new JLabel("班级：",JLabel.CENTER);
		
		studentNameLabel.setFont(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_LABEL_FONT);
		studentGenderLabel.setFont(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_LABEL_FONT);
		studentPhoneLabel.setFont(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_LABEL_FONT);
		studentEmailLabel.setFont(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_LABEL_FONT);
		studentNoLabel.setFont(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_LABEL_FONT);
		studentNoLabel2.setFont(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_LABEL_FONT);
		studentClassLabel.setFont(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_LABEL_FONT);
	}
	
	/**
	 * 初始化文本框
	 */
	private void initTextField() {
		studentNameTextField = new JTextField(15);
		studentGenderTextField = new JTextField(15);
		studentPhoneTextField = new JTextField(15);
		studentEmailTextField = new JTextField(15);
	}
	
	/**
	 * 初始化 update 和 delete 按钮
	 */
	private void initButton() {
		updateButton = new JButton(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_UPDATE_BUTTON);
		updateButton.setFont(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_BUTTON_FONT);
		updateButton.setBackground(new Color(1,158,151));
		updateButton.setForeground(Color.WHITE);
		updateButton.addActionListener(new ButtonActionListener());
		
		deleteButton = new JButton(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_DELETE_BUTTON);
		deleteButton.setFont(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_BUTTON_FONT);
		deleteButton.setBackground(new Color(1,158,151));
		deleteButton.setForeground(Color.WHITE);
		deleteButton.addActionListener(new ButtonActionListener());
	}

	public JLabel getStudentNoLabel2() {
		return studentNoLabel2;
	}

	public JTextField getStudentNameTextField() {
		return studentNameTextField;
	}

	public JTextField getStudentGenderTextField() {
		return studentGenderTextField;
	}

	public JTextField getStudentPhoneTextField() {
		return studentPhoneTextField;
	}

	public JTextField getStudentEmailTextField() {
		return studentEmailTextField;
	}
	
	public UpdateStudentDialog getUpdateStudentDialog() {
		return this;
	}
	
	/**
	 * 无参构造器
	 */
	public UpdateStudentDialog() {
		
	}
	
	/**
	 * 含参构造器
	 */
	public UpdateStudentDialog(TeacherMainFrame frame,String studentNo) {
		super(frame,"学生数据",true);
		this.studentNo = studentNo;
		this.student = frame.getStudentService().findStudentByNo(studentNo).get(0);
		JPanel c = new JPanel();
		initLabel();						//初始化 各种标签
		initTextField();				//初始化 各种文本框
		initButton();					//初始化 按钮
		
		jc = new JComboBox<>(new MyComboBox(frame.getMyClassList()));
		jc.setFont(getFont());
		jc.setSelectedItem(frame.getMyClassList().get(student.getClassId() - 1).getClassName());
		
		c.add(studentNoLabel);				//学生 学号 标签
		c.add(studentNoLabel2);			//显示 具体学号
		c.add(studentNameLabel);			//学生 name
		c.add(studentNameTextField);
		c.add(studentGenderLabel);		//学生 性别
		c.add(studentGenderTextField);	
		c.add(studentPhoneLabel);		//学生 紧急联系电话
		c.add(studentPhoneTextField);	
		c.add(studentEmailLabel);			//学生 邮箱
		c.add(studentEmailTextField);
		c.add(studentClassLabel);
		c.add(jc);
		
		this.setSize(195, 350);
		this.setLocation(350, 100);
		this.setLayout(new BorderLayout());
		this.add(c,BorderLayout.CENTER);
		this.setResizable(false);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
		this.add(buttonPanel,BorderLayout.SOUTH);
	}
	
	/**
	 * 按钮的监听事件
	 */
	class ButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//保存按钮的监听事件
			if(e.getActionCommand().equals(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_UPDATE_BUTTON)) {
				
				student = new Student();
				student.setStudentNo(studentNo);
				student.setStudentName(studentNameTextField.getText());
				student.setStudentGender(studentGenderTextField.getText());
				student.setStudentPhone(studentPhoneTextField.getText());
				student.setStudentEmail(studentEmailTextField.getText());
				student.setClassId(jc.getSelectedIndex() + 1);
				
				if(StudentService.getInstance().updateStudent(student)) {
					new CheckDialog(getUpdateStudentDialog(),"保存成功").setVisible(true);
				}else {
					new CheckDialog(getUpdateStudentDialog(),"保存失败").setVisible(true);
				}
				
			}
			
			//删除按钮的监听事件
			if(e.getActionCommand().equals(TeacherFrameConstant.UPDATE_STUDENT_DIALOG_DELETE_BUTTON)) {
				
				if(StudentService.getInstance().deleteStudent(studentNo)) {
					getUpdateStudentDialog().setVisible(false);
					new CheckDialog(getUpdateStudentDialog(),"删除成功").setVisible(true);
					getUpdateStudentDialog().dispose();
				}else {
					new CheckDialog(getUpdateStudentDialog(),"删除失败").setVisible(true);
				}
				
			}
		}
		
	}
}
