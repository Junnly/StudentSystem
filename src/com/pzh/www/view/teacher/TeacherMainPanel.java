package com.pzh.www.view.teacher;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.pzh.www.constant.TeacherFrameConstant;

/**
 * 老师界面主窗口的登录面板
 * @author Pan梓涵
 */
public class TeacherMainPanel extends JPanel{
	
	private Image img;// 登录面板的背景图片
	/*
	 * 用户主面板的构造方法
	 */
	public TeacherMainPanel() {
		super();											  							// 调用父类JPanel的构造器
		File bgimFile = new File(TeacherFrameConstant.TEACHER_MAIN_FRAME_BGIM_PATH);
		ImageIcon bgim = new ImageIcon(bgimFile.getPath());
		img = bgim.getImage();				  							// 获得登录面板的背景图片
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		// 绘制组件
		g.drawImage(img, 0, 0, this);		// 绘制图片
	}
}
