package com.pzh.www.view.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.pzh.www.view.student.StudentMainFrame;
import com.pzh.www.view.teacher.TeacherMainFrame;
import com.pzh.www.constant.LoginConstant;
import com.pzh.www.service.StudentService;
import com.pzh.www.service.TeacherService;
import com.pzh.www.service.UserService;
import com.pzh.www.view.CheckDialog;
import com.pzh.www.view.user.UserMainFrame;

/**
 * 登录窗体
 * @author Pan梓涵
 * 创建时间：2018/4/10 13:23
 */
public class LoginFrame extends JFrame{
	
	private LoginPanel loginPanel;// 登录面板
	private JTextField usernameTextField;// “用户名”文本框
	private JPasswordField passwordTextField;// “密码”文本框
	private static String userStr;// “用户名”文本框中的内容
	private JRadioButton userButton;// “用户” 单选按钮
	private JRadioButton teacherButton;// “老师” 单选按钮
	private JRadioButton studentButton;// “学生” 单选按钮
	private String checkStr = LoginConstant.RADIO_BUTTON_USER;// 用于检查的字符串
	
	/**
	 * 登录窗体的构造方法
	 */
	public LoginFrame() {
		init();					// 初始化登录窗体
		this.setVisible(true);
		this.setResizable(false);
	}
	
	/**
	 * 初始化loginPanel登录面板的方法
	 */
	private LoginPanel getLoginPanel() {
		if(loginPanel == null) {
			new LoginPanel();
			// “密码”标签
			JLabel passwordLabel = new JLabel("密   码:");
			passwordLabel.setBounds(300,200,80,50);								//设置密码小标签的位置和大小
			passwordLabel.setFont(new Font("微软雅黑",Font.PLAIN,16));//设置密码小标签的字体样式
			passwordLabel.setForeground(Color.WHITE);						//设置密码小标签的字体颜色

			// “用户名”标签
			JLabel usernameLabel = new JLabel("用户名:");
			usernameLabel.setBounds(300, 150, 80, 50);							//设置用户名小标签的位置和大小
			usernameLabel.setFont(new Font("微软雅黑",Font.PLAIN,16));//设置用户名小标签的字体样式
			usernameLabel.setForeground(Color.WHITE);						//设置用户名小标签的字体颜色

			// “标题”标签
			JLabel titleLabel = new JLabel("用户管理系统");
			titleLabel.setBounds(365, 100, 150, 60);
			titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
			titleLabel.setForeground(Color.WHITE);
			
			loginPanel = new LoginPanel();									// 登录面板
			loginPanel.setLayout(null);										// 设置登录面板的布局为绝对布局
			loginPanel.setBackground(Color.BLACK);					// 设置登录面板的背景色
			loginPanel.add(titleLabel,null);									// 把“标题”标签置于登录面板中
			loginPanel.add(usernameLabel, null);						// 把“用户名”标签置于登录面板中
			loginPanel.add(passwordLabel, null);							// 把“密码”标签置于登录面板中
			loginPanel.add(getUsernameTextField(), null);			// 把“用户名”文本框置于登录面板中
			loginPanel.add(getPasswordField(), null);					// 把“密码”文本框置于登录面板中
			loginPanel.add(getLoginButton(), null);						// 把“登录”按钮置于登录面板中
			loginPanel.add(getResetButton(), null);						// 把“退出”按钮置于登录面板中
			
			initRadioButton();
			loginPanel.add(userButton,null);
			loginPanel.add(teacherButton,null);
			loginPanel.add(studentButton,null);
		}
		return loginPanel;// 返回登录面板
		
	}
	
	/**
	 * 初始化“用户名”文本框
	 */
	private JTextField getUsernameTextField() {
		usernameTextField = new JTextField();								//用户名文本框
		usernameTextField.setBounds(380, 165, 160, 20);				//设置用户名文本框的位置和大小
		usernameTextField.setFont(new Font("微软雅黑",Font.PLAIN,13));//设置用户名文本框的字体样式
		return usernameTextField;												// 返回“用户名”文本框
	}
	
	/**
	 * 初始化“密码”文本框
	 */
	private JPasswordField getPasswordField() {
		passwordTextField = new JPasswordField();						//密码文本框
		passwordTextField.setBounds(380, 215, 160, 20);				//设置密码文本框的位置和大小
		passwordTextField.setFont(new Font("微软雅黑",Font.PLAIN,13));//设置密码文本框的字体样式
		return passwordTextField;													// 返回“密码”文本框
	}
	
	/**
	 * 初始化“登录”按钮
	 */
	private JButton getLoginButton() {
		// “登录”按钮
		JButton loginButton = new JButton("登录");
		loginButton.setBounds(330, 275, 70, 30);						//设置登录按钮的位置和大小
		loginButton.setFont(new Font("Bold",Font.PLAIN,12)); //设置登录按钮的字体样式
		loginButton.setForeground(Color.WHITE);						//设置登录按钮的字体颜色
		loginButton.setBackground(new Color(1,158,151));		//设置登录按钮的背景颜色
		//登录按钮的监听事件
		loginButton.addActionListener(new LoginButtonActionListener());
		return loginButton;
	}
	
	/**
	 * 初始化“退出”按钮
	 */
	private JButton getResetButton() {
		// “重置”按钮
		JButton resetButton = new JButton("重置");
		resetButton.setBounds(430, 275, 70, 30);						//设置重置按钮的位置和大小
		resetButton.setFont(new Font("Bold",Font.PLAIN,12));	//设置重置按钮的字体样式
		resetButton.setForeground(Color.WHITE);						//设置重置按钮的字体颜色
		resetButton.setBackground(new Color(1,158,151));		//设置重置按钮的背景颜色
		//重置按钮的监听事件
		resetButton.addActionListener(e -> {
			usernameTextField.setText("");
			passwordTextField.setText("");
		});
		return resetButton;
		
	}
	
	/**
	 * 初始化登录窗体
	 */
	private void init() {
		setLocation(50, 50);									//设置窗口的位置
		setSize(850,480);										//设置窗口大小
		setTitle("信息管理系统-login");					//设置标题
		setContentPane(getLoginPanel());				// 将登录面板置于登录窗体中
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置默认关闭方式
	}
	
	/**
	 * 初始化单选按钮
	 */
	private void initRadioButton() {
		userButton = new JRadioButton(LoginConstant.RADIO_BUTTON_USER,true);
		teacherButton = new JRadioButton(LoginConstant.RADIO_BUTTON_TEACHER);
		studentButton = new JRadioButton(LoginConstant.RADIO_BUTTON_STUDENT);
		userButton.setLocation(325, 240);
		teacherButton.setLocation(385, 240);
		studentButton.setLocation(445, 240);
		userButton.setSize(60, 25);
		teacherButton.setSize(60, 25);
		studentButton.setSize(60, 25);
		userButton.setFont(new Font("Bold",Font.PLAIN,12));
		teacherButton.setFont(new Font("Bold",Font.PLAIN,12));
		studentButton.setFont(new Font("Bold",Font.PLAIN,12));
		userButton.setForeground(Color.WHITE);
		teacherButton.setForeground(Color.WHITE);
		studentButton.setForeground(Color.WHITE);
		userButton.setBackground(new Color(1,158,151));	
		teacherButton.setBackground(new Color(1,158,151));	
		studentButton.setBackground(new Color(1,158,151));
		userButton.addActionListener(new RadioButtonActionListener());
		teacherButton.addActionListener(new RadioButtonActionListener());
		studentButton.addActionListener(new RadioButtonActionListener());
		//   单选按钮组
		ButtonGroup group = new ButtonGroup();
		group.add(userButton);
		group.add(teacherButton);
		group.add(studentButton);
	}
	
	/**
	 * 获取当前窗体的一个引用
	 */
	private LoginFrame getLoginFrame(){
		return this;	
	}
	
	/**
	 * 检查文本框
	 */
	private boolean checkBox() {
		// 用户名不能为空
		if("".equals(usernameTextField.getText().trim())) {
			new CheckDialog(this,"用户名不能为空！").setVisible(true);
			return false;
		}
		if(passwordTextField.getPassword().length < 6) {
			new CheckDialog(this,"密码不能少于六位！").setVisible(true);
			return false;
		}
		return true;
	}
	
	/**
	 * 单选按钮的监听事件
	 */
	class RadioButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(LoginConstant.RADIO_BUTTON_USER.equals(e.getActionCommand())) {
				checkStr = LoginConstant.RADIO_BUTTON_USER;
			}
			if(LoginConstant.RADIO_BUTTON_TEACHER.equals(e.getActionCommand())) {
				checkStr = LoginConstant.RADIO_BUTTON_TEACHER;
			}
			if(LoginConstant.RADIO_BUTTON_STUDENT.equals(e.getActionCommand())) {
				checkStr = LoginConstant.RADIO_BUTTON_STUDENT;
			}
		}	
	}
	
	/**
	 * 登录按钮的监听事件
	 */
	class LoginButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(checkBox()) {
				userStr = usernameTextField.getText();
				switch(checkStr) {
				case "用户": userButtonAction(); break;
				case "老师": teacherButtonAction(); break;
				case "学生": studentButtonAction();break;
				default:
				}
			}
		}
		
		//用户单选按钮的对应方法
		private void userButtonAction() {
			if(UserService.getInstance().login(userStr, passwordTextField.getText())) {
				setVisible(false);							//设置窗体不可见
				new UserMainFrame(userStr);
				getLoginFrame().dispose();
			}else {
				new CheckDialog(getLoginFrame(), LoginConstant.ERROR_STRING).setVisible(true);
			}
		}
		
		//老师单选按钮的对应方法
		private void teacherButtonAction() {
			if(TeacherService.getInstance().teacherLogin(userStr, passwordTextField.getText())) {
				setVisible(false);							//设置窗体不可见
				new TeacherMainFrame(userStr);
				getLoginFrame().dispose();
			}else {
				new CheckDialog(getLoginFrame(), LoginConstant.ERROR_STRING).setVisible(true);
			}
		}
		
		//学生单选按钮的对应方法
		private void studentButtonAction() {
			if(StudentService.getInstance().studentLogin(userStr, passwordTextField.getText())) {
				setVisible(false);							//设置窗体不可见
				new StudentMainFrame(userStr);
				getLoginFrame().dispose();
			}else {
				new CheckDialog(getLoginFrame(), LoginConstant.ERROR_STRING).setVisible(true);
			}
		}
		
	}
}
