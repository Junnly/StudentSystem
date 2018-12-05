package com.pzh.www.view;

import javax.swing.JFrame;

import com.pzh.www.view.login.LoginFrame;

/**
 * 主窗体模块
 * @author Pan梓涵
 *
 */
public class MainView extends JFrame{

	private static final long serialVersionUID = 6376932599471649292L;

	public static void main(String[] args) {
		new LoginFrame();
	}

}
