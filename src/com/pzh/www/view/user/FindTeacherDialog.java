package com.pzh.www.view.user;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.pzh.www.view.CheckDialog;
import com.pzh.www.constant.UserFrameConstant;
import com.pzh.www.po.Teacher;

/**
 * 查找老师窗口
 * @author Pan梓涵
 */
public class FindTeacherDialog extends JDialog{
	
	private JLabel teacherNoLabel;						//老师 编号 标签
	private JLabel nameLabel;								//老师 name 标签
	private JTextField teacherNoTextField;			//老师 编号 文本框
	private JTextField nameTextField;					//老师 name 文本框
	private JButton findByNoButton;						//根据 编号 查询的查询按钮
	private JButton findByNameButton;				//根据 name 查询的查询按钮
	private List<Teacher> list = null;						//用于存放查询结果集
	private UserMainFrame userMainFrame;			//保留对窗体的引用
	
	/**
	 * 初始化 标签
	 */
	private void initLabel() {
		teacherNoLabel = new JLabel("根据teacherNo查询   No：",JLabel.CENTER);
		teacherNoLabel.setFont(new Font("微软雅黑",Font.PLAIN,13));
		nameLabel = new JLabel("根据name的模糊查询   name：",JLabel.CENTER);
		nameLabel.setFont(new Font("微软雅黑",Font.PLAIN,13));
	}
	
	/**
	 * 初始化 文本框
	 */
	private void initTextField() {
		teacherNoTextField = new JTextField(15);
		nameTextField = new JTextField(15);
	}

	public JTextField getteacherNoTextField() {
		return teacherNoTextField;
	}
	
	public JTextField getNameTextField() {
		return nameTextField;
	}
	
	/**
	 * 初始化 查询(id) 确定按钮
	 */
	private JButton getFindByNoButton() {
		findByNoButton = new JButton(UserFrameConstant.FIND_TEACHER_DIALOG_FIND_BYNO_BUTTON);
		findByNoButton.setFont(UserFrameConstant.FIND_TEACHER_DIALOG_BUTTON_FONT);
		findByNoButton.setBackground(new Color(1,158,151));
		findByNoButton.setForeground(Color.WHITE);
		// 查询(No) 确定按钮的监听事件
		findByNoButton.addActionListener(e -> {
			if(teacherNoTextField.getText().trim().length() > 0) {
				list = userMainFrame.getTeacherService().findTeacherByNo(teacherNoTextField.getText());
				if(list.size() > 0) {
					getFindTeacherDialog().setVisible(false);
					userMainFrame.setList(list);
				}else {
					new CheckDialog(getFindTeacherDialog(), "sorry,数据库无此资料！").setVisible(true);
				}
			}else {
				new CheckDialog(getFindTeacherDialog(), "查询No不能为空！").setVisible(true);;
			}
		});
		return findByNoButton;
	}
	
	/**
	 * 初始化 查询(name) 确定按钮
	 */
	private JButton getFindByNameButton() {
		findByNameButton = new JButton(UserFrameConstant.FIND_TEACHER_DIALOG_FIND_BYNAME_BUTTON);
		findByNameButton.setFont(UserFrameConstant.FIND_TEACHER_DIALOG_BUTTON_FONT);
		findByNameButton.setBackground(new Color(1,158,151));
		findByNameButton.setForeground(Color.WHITE);
		// 查询(name) 确定按钮的监听事件
		findByNameButton.addActionListener(e -> {
			if(nameTextField.getText().trim().length() > 0) {
				list = userMainFrame.getTeacherService().findTeacherByName(nameTextField.getText());
				if(list.size() > 0) {
					getFindTeacherDialog().setVisible(false);
					getFindTeacherDialog().dispose();
					userMainFrame.setList(list);
				}else {
					new CheckDialog(getFindTeacherDialog(), "sorry,数据库无此资料！").setVisible(true);
				}
			}else {
				new CheckDialog(getFindTeacherDialog(), "查询name不能为空！").setVisible(true);
			}
		});
		return findByNameButton;
	}
	
	/**
	 * 无参数构造器
	 */
	public FindTeacherDialog(){
		
	}
	
	/**
	 * 含参数构造器
	 */
	public FindTeacherDialog(JFrame frame) {
		super(frame);
		if(frame instanceof UserMainFrame) {
			userMainFrame = (UserMainFrame) frame;
		}
		init();
		setVisible(true);
		setResizable(false);
	}
	
	/**
	 * 初始化 窗体
	 */
	private void init() {
		this.initLabel();			//初始化标签
		this.initTextField(); 	//初始化文本框
		this.setTitle("查找");
		this.setSize(227, 237);
		this.setLocation(350, 100);
		this.setLayout(new FlowLayout());
		this.add(teacherNoLabel);
		this.add(teacherNoTextField);
		this.add(getFindByNoButton());
		this.add(nameLabel);
		this.add(nameTextField);
		this.add(getFindByNameButton());
	}
	
	private FindTeacherDialog getFindTeacherDialog() {
		return this;
	}
}
