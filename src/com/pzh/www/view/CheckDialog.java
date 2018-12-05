package com.pzh.www.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.pzh.www.view.login.LoginFrame;
import com.pzh.www.constant.MyConstant;

/**
 * 提示窗体
 * @author Pan梓涵
 * 创建时间2018/4/10 21:04
 */
public class CheckDialog extends JDialog{

	private static final long serialVersionUID = -211798091661816590L;
	
	public CheckDialog() {
		
	}
	
	/**
	 * 登录界面的提示小窗体
	 */
	public CheckDialog(LoginFrame loginFrame, String s) {
		super(loginFrame,MyConstant.CHECK_DIALOG_TITLE,true);
		Container container = getContentPane();
		JLabel jlabel = new JLabel(s);
		jlabel.setFont(new Font("微软雅黑",Font.PLAIN,14));
		jlabel.setForeground(Color.red);
		container.add(jlabel);
		setBounds(300, 260, 150, 100);
		
	}

	/**
	 * Add资料界面的提示小窗体1
	 * @param owner
	 */
	public CheckDialog(JDialog owner) {
		super(owner,MyConstant.CHECK_DIALOG_TITLE);
		setLocation(340, 200);
	}

	/**
	 * Add资料界面的提示小窗体2
	 * @param owner
	 * @param s
	 */
	public CheckDialog(JDialog owner,String s) {
		super(owner,MyConstant.CHECK_DIALOG_TITLE,true);
		Container container = getContentPane();
		JLabel jlabel = new JLabel(s);
		jlabel.setFont(new Font("微软雅黑",Font.PLAIN,14));
		jlabel.setForeground(Color.red);
		container.add(jlabel);
		setBounds(340, 200, 250, 120);
	}
	
	public CheckDialog(JFrame frame,String s) {
		super(frame,MyConstant.CHECK_DIALOG_TITLE,true);
		Container container = getContentPane();
		JLabel jlabel = new JLabel(s);
		jlabel.setFont(new Font("微软雅黑",Font.PLAIN,14));
		jlabel.setForeground(Color.red);
		container.add(jlabel);
		setBounds(340, 200, 180, 120);
	}
	
}
