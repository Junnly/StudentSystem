package com.pzh.www.view.login;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.pzh.www.constant.LoginConstant;

/**
 * 登录面板
 * 
 * @author Pan梓涵 创建时间：2018/4/10 13：16
 */
public class LoginPanel extends JPanel {

	private static final long serialVersionUID = -3552784784802099371L;
	
	private Image image;// 登录面板的背景图片

	LoginPanel() {
		//调用父类JPanel的构造器
		super();
		File imageFile = new File(LoginConstant.LOGIN_PANEL_IMAGE_PATH);
		//获得登录面板的背景图片
		image = new ImageIcon(imageFile.getPath()).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// 绘制组件
		super.paintComponent(g);
		// 绘制图片
		g.drawImage(image, 0, 0, this);
	}
	
	
}
